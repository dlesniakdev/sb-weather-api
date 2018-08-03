package pl.com.bbzoftware.sbi.weather.transformer.openweathermap.transformer;

import org.openweathermap.api.model.MainParameters;
import org.openweathermap.api.model.forecast.ForecastInformation;
import org.openweathermap.api.model.forecast.hourly.HourlyForecast;
import org.springframework.stereotype.Component;
import pl.com.bbzoftware.sbi.weather.model.*;
import pl.com.bbzoftware.sbi.weather.transformer.WeatherTransformer;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OpenWeatherMapTransformer implements WeatherTransformer<ForecastInformation<HourlyForecast>> {
  @Override
  public SbForecasts transform(final ForecastInformation<HourlyForecast> openWeatherApiResult) {
    List<SbForecast> forecasts = openWeatherApiResult.getForecasts()
        .stream()
        .map(forecast -> {
          MainParameters mp = forecast.getMainParameters();
          return SbForecast.builder()
              .calculationDate(convertToLocalDate(forecast.getCalculationDate()))
              .humidity(buildHumidity(mp))
              .pressure(buildPressure(mp))
              .temperature(buildTemperature(mp))
              .build();
        })
        .collect(Collectors.toList());
    return new SbForecasts(forecasts);
  }

  private LocalDateTime convertToLocalDate(Date dateToConvert) {
    return Instant.ofEpochMilli(dateToConvert.getTime())
        .atZone(ZoneId.of("UTC"))
        .toLocalDateTime();
  }

  private SbHumidity buildHumidity(final MainParameters mp) {
    return SbHumidity.builder()
        .unit("%")
        .value(mp.getHumidity())
        .build();
  }

  private SbPressure buildPressure(final MainParameters mp) {
    return SbPressure.builder()
        .unit("hPa")
        .value(mp.getPressure())
        .build();
  }

  private SbTemperature buildTemperature(final MainParameters mp) {
    return SbTemperature.builder()
        .unit("C")
        .value(mp.getTemperature())
        .min(mp.getMinimumTemperature())
        .max(mp.getMaximumTemperature())
        .build();
  }
}
