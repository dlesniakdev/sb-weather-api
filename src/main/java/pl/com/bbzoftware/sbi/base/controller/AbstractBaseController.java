package pl.com.bbzoftware.sbi.base.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import pl.com.bbzoftware.sbi.base.exception.SbException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

abstract public class AbstractBaseController {

  @ExceptionHandler(value = SbException.class)
  public ModelAndView exceptionHandler(final HttpServletRequest req,
                                       final HttpServletResponse res,
                                       final SbException ex) {
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setView(new MappingJackson2JsonView());
    modelAndView.addObject("statusCode", ex.getStatusCode().value());
    modelAndView.addObject("statusText", ex.getStatusText());
    modelAndView.addObject("url", req.getRequestURL());
    res.setStatus(ex.getStatus().value());
    return modelAndView;
  }
}
