package pl.com.bbzoftware.sbi.weather.smoke


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Profile
import pl.com.bbzoftware.sbi.weather.controller.WeatherController
import spock.lang.Specification

@SpringBootTest
@Profile('test')
class SmokeTest extends Specification {

  @Autowired
  WeatherController controller

  def 'context should load'() {
    expect:
    controller
  }
}
