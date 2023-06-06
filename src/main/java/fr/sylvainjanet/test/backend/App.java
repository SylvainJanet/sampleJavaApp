package fr.sylvainjanet.test.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import fr.sylvainjanet.test.backend.entities.Message;
import fr.sylvainjanet.test.backend.repo.MessageRepository;

/**
 * App.
 *
 * @author Sylvain Janet
 *
 */
@Controller
@EnableConfigurationProperties
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@ConfigurationProperties(prefix = "app")
public class App extends SpringBootServletInitializer {

  /**
   * Profile name from app.properties.
   */
  private String environment;

  /**
   * getProfile.
   * 
   * @return the environment
   */
  public String getEnvironment() {
    return environment;
  }

  /**
   * setProfile.
   * 
   * @param environmentToSet the profile
   */
  public void setEnvironment(final String environmentToSet) {
    this.environment = environmentToSet;
  }

  /**
   * The repo.
   */
  @Autowired
  private MessageRepository repository;

  /**
   * home.
   *
   * @return hello
   */
  @GetMapping("/hello")
  @ResponseBody
  String home() {
    return "Hello World ! - " + environment;
  }

  /**
   * Create new message in db.
   * 
   * @param content the content to put
   * @return a confirmation message
   */
  @PutMapping(path = "/add-message", produces = "text/plain")
  ResponseEntity<String> addMessage(
      @RequestParam(required = true) final String content) {

    repository.save(new Message(content));
    return new ResponseEntity<>("message \"" + content + "\" added.",
        HttpStatus.OK);
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
