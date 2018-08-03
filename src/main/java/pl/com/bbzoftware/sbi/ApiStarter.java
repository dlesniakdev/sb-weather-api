package pl.com.bbzoftware.sbi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class ApiStarter extends SpringBootServletInitializer {

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder app) {
    return app.sources(ApiStarter.class);
  }

  public static void main(String... args) {
    SpringApplication.run(ApiStarter.class, args);
  }
}
