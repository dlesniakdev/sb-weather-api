package pl.com.bbzoftware.sbi.weather.facade;

import org.openweathermap.api.model.forecast.ForecastInformation;
import org.openweathermap.api.model.forecast.hourly.HourlyForecast;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import pl.com.bbzoftware.sbi.base.exception.SbException;
import pl.com.bbzoftware.sbi.weather.gateway.WeatherApiGateway;
import pl.com.bbzoftware.sbi.weather.model.SbForecasts;
import pl.com.bbzoftware.sbi.weather.parameter.WeatherParameterContext;
import pl.com.bbzoftware.sbi.weather.transformer.WeatherTransformer;

@Component
public class WeatherFacadeImpl implements WeatherFacade {

  @Autowired
  private WeatherApiGateway weatherApiGateway;

  @Autowired
  private WeatherTransformer<ForecastInformation<HourlyForecast>> weatherTransformer;

  @Override
  public SbForecasts get(WeatherParameterContext parameterContext) {
    return weatherApiGateway.get(parameterContext)
        .map(weatherTransformer::transform)
        .orElseThrow(() -> new SbException(HttpStatus.NOT_FOUND));
  }
}
