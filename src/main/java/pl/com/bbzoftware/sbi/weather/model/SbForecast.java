package pl.com.bbzoftware.sbi.weather.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
public class SbForecast {
  private SbTemperature temperature;
  private SbPressure pressure;
  private SbHumidity humidity;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
  private LocalDateTime calculationDate;
}
