package pl.com.bbzoftware.sbi.weather.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SbHumidity {
  private String unit;
  private Double value;
}
