package pl.com.bbzoftware.sbi.weather.facade

import org.springframework.http.HttpStatus
import pl.com.bbzoftware.sbi.base.exception.SbException
import pl.com.bbzoftware.sbi.weather.gateway.WeatherApiGateway
import pl.com.bbzoftware.sbi.weather.model.SbForecasts
import pl.com.bbzoftware.sbi.weather.mother.WeatherParameterContextMother
import pl.com.bbzoftware.sbi.weather.transformer.WeatherTransformer
import spock.lang.Specification

import static pl.com.bbzoftware.sbi.weather.model.City.LONDON
import static pl.com.bbzoftware.sbi.weather.mother.OpenWeatherMapResultMother.sampleOpenWeatherMapResult

class WeatherFacadeImplTest extends Specification {

  def weatherApiGateway = Mock(WeatherApiGateway)
  def weatherTransformer = Mock(WeatherTransformer)
  def objectUnderTest = new WeatherFacadeImpl(
      weatherApiGateway: weatherApiGateway,
      weatherTransformer: weatherTransformer
  )

  final WEATHER_API_RESPONSE = sampleOpenWeatherMapResult()
  final SB_API_RESPONSE = new SbForecasts()

  def 'should execute get for weatherApi and transform result'() {
    given:
    def parameterContext = WeatherParameterContextMother.contextWithCity(LONDON)

    when:
    def result = objectUnderTest.get(parameterContext)

    then:
    1 * weatherApiGateway.get(parameterContext) >> Optional.of(WEATHER_API_RESPONSE)
    1 * weatherTransformer.transform(WEATHER_API_RESPONSE) >> SB_API_RESPONSE

    and:
    result == SB_API_RESPONSE
  }

  def 'should not transform result if it wasnt returned'() {
    given:
    def parameterContext = WeatherParameterContextMother.contextWithCity(LONDON)

    when:
    objectUnderTest.get(parameterContext)

    then:
    1 * weatherApiGateway.get(parameterContext) >> Optional.empty()
    0 * weatherTransformer.transform(_)

    and:
    def ex = thrown(SbException)
    ex.status == HttpStatus.NOT_FOUND
  }
}
