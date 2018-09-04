package pl.com.bbzoftware.sbi.weather.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.com.bbzoftware.sbi.base.controller.AbstractBaseController;
import pl.com.bbzoftware.sbi.weather.facade.WeatherFacade;
import pl.com.bbzoftware.sbi.weather.model.SbForecasts;
import pl.com.bbzoftware.sbi.weather.parameter.WeatherParameterContext;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/sbforecast")
public class WeatherController extends AbstractBaseController {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private WeatherFacade weatherFacade;

  @CrossOrigin(origins = "*")
  @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public SbForecasts getWeather(HttpServletRequest request) {
    WeatherParameterContext parameterContext = WeatherParameterContext.from(request);
    logger.debug("/api/sbforecast request with parameters: " + parameterContext);
    return weatherFacade.get(parameterContext);
  }
}
