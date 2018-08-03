package pl.com.bbzoftware.sbi.weather.facade;

import pl.com.bbzoftware.sbi.weather.model.SbForecasts;
import pl.com.bbzoftware.sbi.weather.parameter.WeatherParameterContext;

public interface WeatherFacade {

    SbForecasts get(WeatherParameterContext parameterContext);
}
