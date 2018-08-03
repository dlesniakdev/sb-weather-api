package pl.com.bbzoftware.sbi.weather.controller

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultMatcher
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import pl.com.bbzoftware.sbi.base.parameter.Parameter
import pl.com.bbzoftware.sbi.weather.TestConfig
import pl.com.bbzoftware.sbi.weather.gateway.WeatherApiGateway
import pl.com.bbzoftware.sbi.weather.model.City
import pl.com.bbzoftware.sbi.weather.transformer.WeatherTransformer
import spock.lang.Specification
import spock.lang.Unroll

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import static pl.com.bbzoftware.sbi.weather.mother.OpenWeatherMapResultMother.sampleOpenWeatherMapResult

@SpringBootTest
@AutoConfigureMockMvc
@Import(TestConfig)
class WeatherControllerIT extends Specification {
  @Autowired
  MockMvc mvc

  @Autowired
  WeatherApiGateway weatherApiGateway

  @Autowired
  WeatherTransformer weatherTransformer

  @Autowired
  private ObjectMapper jacksonObjectMapper

  final WEATHER_API_RESPONSE = sampleOpenWeatherMapResult()

  def "should load spring context and serve mocked data"() {
    given:
    weatherApiGateway.get(_) >> Optional.of(WEATHER_API_RESPONSE)

    expect: "controller is available"
    mvc.perform(MockMvcRequestBuilders.get("/api/sbforecast/")
        .param(Parameter.CITY.name, City.LONDON.paramValue)
        .header('Authorization', 'Basic dXNlcjp1c2Vy')
    )
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(formatedResponseInJson())
  }

  def "should respond with 404 Not Found if weather api doesnt return nothing"() {
    given:
    weatherApiGateway.get(_) >> Optional.empty()

    expect: "controller is available"
    mvc.perform(MockMvcRequestBuilders.get("/api/sbforecast/")
        .param(Parameter.CITY.name, City.LONDON.paramValue)
        .header('Authorization', 'Basic dXNlcjp1c2Vy')
    )
        .andExpect(status().isNotFound())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
  }

  @Unroll
  def "should respond with #statusCode if validation doesnt pass for #parameter=#parameterValue"() {
    expect: "controller is available"
    mvc.perform(MockMvcRequestBuilders.get("/api/sbforecast/")
        .param(parameter, parameterValue)
        .header('Authorization', 'Basic dXNlcjp1c2Vy')
    )
        .andExpect(expectedStatus)
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))

    where:
    expectedStatus                   | statusCode | parameter           | parameterValue
    status().isUnprocessableEntity() | 422        | Parameter.CITY.name | 'Bytom'
    status().isPreconditionFailed()  | 413        | 'unknownParameter'  | 'value'
  }

  @Unroll
  def "should respond with 422 if validation doesnt pass for query without required params"() {
    expect: "controller is available"
    mvc.perform(MockMvcRequestBuilders.get("/api/sbforecast/")
        .header('Authorization', 'Basic dXNlcjp1c2Vy')
    )
        .andExpect(status().isUnprocessableEntity())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
  }

  protected ResultMatcher formatedResponseInJson() {
    content().json(
        jacksonObjectMapper.writeValueAsString(
            weatherTransformer.transform(WEATHER_API_RESPONSE)
        )
    )
  }

}
