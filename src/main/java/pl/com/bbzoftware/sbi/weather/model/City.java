package pl.com.bbzoftware.sbi.weather.model;

import java.util.Arrays;

public enum City {
  LONDON("london"),
  WASHINGTON("washington"),
  NEW_YORK("new york");

  private String paramValue;

  City(final String paramValue) {
    this.paramValue = paramValue;
  }

  public String getParamValue() {
    return paramValue;
  }

  public static boolean isSupported(String cityName) {
    return Arrays.stream(values())
        .anyMatch(city -> city.paramValue.equalsIgnoreCase(cityName));
  }

  public static City fromString(String cityName) {
    return Arrays.stream(values())
        .filter(city -> city.paramValue.equalsIgnoreCase(cityName))
        .findFirst()
        .get();
  }
}
