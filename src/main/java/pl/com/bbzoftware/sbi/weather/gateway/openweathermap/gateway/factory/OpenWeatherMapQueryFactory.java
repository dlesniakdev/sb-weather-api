package pl.com.bbzoftware.sbi.weather.gateway.openweathermap.gateway.factory;

import com.google.common.net.UrlEscapers;
import org.openweathermap.api.query.Language;
import org.openweathermap.api.query.QueryBuilderPicker;
import org.openweathermap.api.query.UnitFormat;
import org.openweathermap.api.query.forecast.hourly.ByCityName;
import pl.com.bbzoftware.sbi.weather.parameter.WeatherParameterContext;

public class OpenWeatherMapQueryFactory {

  public static ByCityName fiveDaysForecastForCity(final WeatherParameterContext parameterContext) {
    return QueryBuilderPicker.pick()
        .forecast()
        .hourly()
        .byCityName(getCityNameEncoded(parameterContext))
        .unitFormat(UnitFormat.METRIC)
        .language(Language.ENGLISH)
        .build();
  }

  private static String getCityNameEncoded(final WeatherParameterContext parameterContext) {
    return UrlEscapers.urlPathSegmentEscaper().escape(parameterContext.getCity().getParamValue());
  }
}
