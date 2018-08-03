package pl.com.bbzoftware.sbi.weather

import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import pl.com.bbzoftware.sbi.weather.gateway.WeatherApiGateway
import spock.mock.DetachedMockFactory

@TestConfiguration
class TestConfig {
  def mockFactory = new DetachedMockFactory()

  @Bean
  WeatherApiGateway weatherApiGateway() {
    return mockFactory.Mock(WeatherApiGateway)
  }
}
