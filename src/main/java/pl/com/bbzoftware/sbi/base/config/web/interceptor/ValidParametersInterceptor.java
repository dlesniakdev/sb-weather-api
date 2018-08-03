package pl.com.bbzoftware.sbi.base.config.web.interceptor;

import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import pl.com.bbzoftware.sbi.base.exception.SbException;
import pl.com.bbzoftware.sbi.base.parameter.Parameter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

public class ValidParametersInterceptor extends HandlerInterceptorAdapter {
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    containsOnlyValidParameters(request.getParameterNames());
    return true;
  }

  private void containsOnlyValidParameters(Enumeration<String> parameterNames) {
    while (parameterNames.hasMoreElements()) {
      String param = parameterNames.nextElement();
      if(!Parameter.isValid(param)) {
        throw new SbException(HttpStatus.PRECONDITION_FAILED,
            "Invalid parameter: " + param);
      }
    }
  }

}
