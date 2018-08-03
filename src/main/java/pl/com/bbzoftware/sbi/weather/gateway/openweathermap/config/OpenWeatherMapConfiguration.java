package pl.com.bbzoftware.sbi.weather.gateway.openweathermap.config;

import org.openweathermap.api.DataWeatherClient;
import org.openweathermap.api.UrlConnectionDataWeatherClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenWeatherMapConfiguration {

  @Value("${openweathermap.apikey}")
  private String apiKey;

  @Bean
  public DataWeatherClient dataWeatherClient() {
    return new UrlConnectionDataWeatherClient(apiKey);
  }
}
