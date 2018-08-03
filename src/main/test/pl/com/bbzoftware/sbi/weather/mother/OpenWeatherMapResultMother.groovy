package pl.com.bbzoftware.sbi.weather.mother

import org.openweathermap.api.model.MainParameters
import org.openweathermap.api.model.forecast.ForecastInformation
import org.openweathermap.api.model.forecast.hourly.HourlyForecast

class OpenWeatherMapResultMother {

  static def sampleOpenWeatherMapResult() {
    new ForecastInformation<HourlyForecast>().with {
      forecasts = [sampleForecast(), sampleForecast()]
      it
    }
  }

  private static def sampleForecast() {
    new HourlyForecast().with {
      calculationDate = new Date()
      mainParameters = sampleMainParameters()
      it
    }
  }

  private static def sampleMainParameters() {
    new MainParameters().with {
      temperature = 5.0
      minimumTemperature = 2.0
      maximumTemperature = 6.0
      humidity = 100
      pressure = 1000
      it
    }
  }
}
