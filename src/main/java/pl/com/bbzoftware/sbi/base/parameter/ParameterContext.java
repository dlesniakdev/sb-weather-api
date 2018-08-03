package pl.com.bbzoftware.sbi.base.parameter;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
public class ParameterContext {

  private final Map<Parameter, String> parameters;

  public static ParameterContext fromRequest(HttpServletRequest request) {
    Map<Parameter, String> paramsFromRequest = gatherParametersFromRequest(request);
    return new ParameterContext(paramsFromRequest);
  }

  private static Map<Parameter, String> gatherParametersFromRequest(HttpServletRequest request) {
    return Arrays.stream(Parameter.values())
        .filter(parameter -> request.getParameter(parameter.getName()) != null)
        .collect(
            Collectors.toMap(
                parameter -> parameter,
                parameter -> request.getParameter(parameter.getName()))
        );
  }

  public Optional<String> getParameter(Parameter parameter) {
    return Optional.ofNullable(parameters.get(parameter));
  }

  @Override
  public String toString() {
    return "ParameterContext{" +
        "parameters=" + parameters +
        '}';
  }
}

