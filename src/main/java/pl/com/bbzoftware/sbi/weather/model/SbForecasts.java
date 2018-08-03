package pl.com.bbzoftware.sbi.weather.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class SbForecasts {
  private final List<SbForecast> forecasts;
}
