package pl.com.bbzoftware.sbi.weather.gateway;

import org.openweathermap.api.model.forecast.ForecastInformation;
import org.openweathermap.api.model.forecast.hourly.HourlyForecast;
import pl.com.bbzoftware.sbi.weather.parameter.WeatherParameterContext;

import java.util.Optional;

public interface WeatherApiGateway {
  Optional<ForecastInformation<HourlyForecast>> get(WeatherParameterContext parameterContext);
}