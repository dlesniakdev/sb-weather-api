package pl.com.bbzoftware.sbi.weather.transformer.openweathermap.transformer

import pl.com.bbzoftware.sbi.weather.mother.OpenWeatherMapResultMother
import spock.lang.Specification

import java.time.ZoneOffset

class OpenWeatherMapTransformerTest extends Specification {

  def objectUnderTest = new OpenWeatherMapTransformer()

  def 'should transform object'() {
    given:
    def openWeatherMapResult = OpenWeatherMapResultMother.sampleOpenWeatherMapResult()

    when:
    def result = objectUnderTest.transform(openWeatherMapResult)

    then:
    result
    result.forecasts.size() == openWeatherMapResult.forecasts.size()
    result.forecasts.eachWithIndex { def forecast, int i ->
      assert forecast.calculationDate.toInstant(ZoneOffset.UTC).toEpochMilli() == openWeatherMapResult.forecasts[i].calculationDate.toInstant().toEpochMilli()
      assert forecast.temperature.value == openWeatherMapResult.forecasts[i].mainParameters.temperature
      assert forecast.temperature.min == openWeatherMapResult.forecasts[i].mainParameters.minimumTemperature
      assert forecast.temperature.max == openWeatherMapResult.forecasts[i].mainParameters.maximumTemperature
      assert forecast.pressure.value == openWeatherMapResult.forecasts[i].mainParameters.pressure
      assert forecast.humidity.value == openWeatherMapResult.forecasts[i].mainParameters.humidity
    }
  }
}
