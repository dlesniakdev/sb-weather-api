package pl.com.bbzoftware.sbi.weather.transformer;

import pl.com.bbzoftware.sbi.weather.model.SbForecasts;

public interface WeatherTransformer<T> {
  SbForecasts transform(T forecast);
}
