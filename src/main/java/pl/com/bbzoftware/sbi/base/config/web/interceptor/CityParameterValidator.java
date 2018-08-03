package pl.com.bbzoftware.sbi.base.config.web.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import pl.com.bbzoftware.sbi.base.exception.SbException;
import pl.com.bbzoftware.sbi.base.parameter.Parameter;
import pl.com.bbzoftware.sbi.weather.model.City;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CityParameterValidator extends HandlerInterceptorAdapter {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    String city = request.getParameter(Parameter.CITY.getName());
    isPresent(city);
    isValueValid(city);
    return true;
  }

  private void isPresent(final String city) {
    if (city == null) {
      logger.debug("Required parameter[city] is not present.");
      throw new SbException((HttpStatus.UNPROCESSABLE_ENTITY),
          "Required parameter[city] is not present.");
    }
  }

  private void isValueValid(String city) {
    if (!City.isSupported(city)) {
      logger.debug("Invalid parameter[city] value: " + city);
      throw new SbException(HttpStatus.UNPROCESSABLE_ENTITY,
          "Invalid parameter[city] value: " + city);
    }
  }
}
