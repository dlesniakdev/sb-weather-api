package pl.com.bbzoftware.sbi.weather.gateway.openweathermap.gateway;

import org.openweathermap.api.DataWeatherClient;
import org.openweathermap.api.model.forecast.ForecastInformation;
import org.openweathermap.api.model.forecast.hourly.HourlyForecast;
import org.openweathermap.api.query.forecast.hourly.ByCityName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import pl.com.bbzoftware.sbi.weather.gateway.WeatherApiGateway;
import pl.com.bbzoftware.sbi.weather.parameter.WeatherParameterContext;

import java.util.Optional;

import static java.lang.String.format;
import static pl.com.bbzoftware.sbi.weather.gateway.openweathermap.gateway.factory.OpenWeatherMapQueryFactory.fiveDaysForecastForCity;

@Component
public class OpenWeatherMapApiGateway implements WeatherApiGateway {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private DataWeatherClient client;

  @Override
  @Cacheable(value = "owm-cache", key = "#parameterContext.getCity()")
  public Optional<ForecastInformation<HourlyForecast>> get(final WeatherParameterContext parameterContext) {
    ByCityName query = fiveDaysForecastForCity(parameterContext);
    ForecastInformation<HourlyForecast> forecastInformation =
        client.getForecastInformation(query);
    logger.info(format("Request to OpenWeatherMap API executed, response for %s cached for configured period of time.",
        parameterContext.getCity()));
    return Optional.ofNullable(forecastInformation);
  }

}
