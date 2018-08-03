package pl.com.bbzoftware.sbi.weather.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SbTemperature {
  private String unit;
  private Double value;
  private Double min;
  private Double max;
}
