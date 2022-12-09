package fr.sylvainjanet.test.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * App.
 *
 * @author Sylvain Janet
 *
 */
@Controller
@EnableAutoConfiguration
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class App extends SpringBootServletInitializer {
  /**
   * home.
   *
   * @return hello
   */
  @GetMapping("/hello")
  @ResponseBody
  String home() {
    return "Hello World !!  !!";
  }

  /**
   * Main.
   *
   * @param args args
   * @throws Exception exception
   */
  public static void main(final String[] args) {
    SpringApplication.run(App.class, args);
  }

  /**
   * configure.
   */
  @Override
  protected SpringApplicationBuilder configure(
      final SpringApplicationBuilder builder) {
    return builder.sources(App.class);
  }

}
