package pl.com.bbzoftware.sbi.weather.parameter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.com.bbzoftware.sbi.base.parameter.Parameter;
import pl.com.bbzoftware.sbi.base.parameter.ParameterContext;
import pl.com.bbzoftware.sbi.weather.model.City;

import javax.servlet.http.HttpServletRequest;

import static pl.com.bbzoftware.sbi.base.parameter.ParameterContext.fromRequest;

@AllArgsConstructor
@Getter
public class WeatherParameterContext {

  private final ParameterContext parameterContext;

  public static WeatherParameterContext from(HttpServletRequest request) {
    return new WeatherParameterContext(fromRequest(request));
  }

  public City getCity() {
    return parameterContext.getParameter(Parameter.CITY)
        .map(City::fromString)
        .get();
  }

  @Override
  public String toString() {
    return "WeatherParameterContext{" +
        "parameterContext=" + parameterContext +
        '}';
  }
}
