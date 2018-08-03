package pl.com.bbzoftware.sbi.weather.mother

import pl.com.bbzoftware.sbi.base.parameter.Parameter
import pl.com.bbzoftware.sbi.base.parameter.ParameterContext
import pl.com.bbzoftware.sbi.weather.model.City
import pl.com.bbzoftware.sbi.weather.parameter.WeatherParameterContext

class WeatherParameterContextMother {

  static contextWithCity(City city) {
    new WeatherParameterContext(parameterContext(city))
  }

  static ParameterContext parameterContext(final City city) {
    new ParameterContext([(Parameter.CITY): city.paramValue])
  }
}
