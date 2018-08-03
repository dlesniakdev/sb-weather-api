package pl.com.bbzoftware.sbi.base.config.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pl.com.bbzoftware.sbi.base.config.web.interceptor.CityParameterValidator;
import pl.com.bbzoftware.sbi.base.config.web.interceptor.ValidParametersInterceptor;

@EnableWebMvc
@Configuration
public class WebApplicationConfig implements WebMvcConfigurer {

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new ValidParametersInterceptor());
    registry.addInterceptor(new CityParameterValidator());
  }

}
