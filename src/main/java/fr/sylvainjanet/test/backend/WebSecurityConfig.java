package fr.sylvainjanet.test.backend;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * Web Security Config.
 *
 * @author Sylvain Janet
 *
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

  @Bean
  static AuthenticationManager authenticationManagerBean(
      final AuthenticationConfiguration authenticationConfiguration)
      throws Exception {
    // ALTHOUGH THIS SEEMS LIKE USELESS CODE,
    // IT'S REQUIRED TO PREVENT SPRING BOOT AUTO-CONFIGURATION
    return authenticationConfiguration.getAuthenticationManager();
  }

  /**
   * Current environment.
   */
  @Value("${app.environment}")
  private String environment;

  /**
   * cors Configuration.
   *
   * @return cors Configuration
   */
  @Bean
  @Primary
  CorsConfigurationSource corsConfiguration() {
    CorsConfiguration corsConfig = new CorsConfiguration();

    corsConfig.setAllowedOrigins(Arrays.asList("https://sylvainjanet.fr",
        "https://dev.sylvainjanet.fr"));
    corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE",
        "PUT", "OPTIONS", "PATCH", "DELETE"));
    corsConfig.setAllowCredentials(true);

    if (environment.equals("dev") || environment.equals("coverage-dev")) {
      corsConfig.setAllowedOrigins(Arrays.asList("*"));
      corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE",
          "PUT", "OPTIONS", "PATCH", "DELETE"));
      corsConfig.setAllowCredentials(false);
    }

    UrlBasedCorsConfigurationSource source =
        new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", corsConfig);
    return source;
  }

  /**
   * Set security.
   *
   * @param http the http object
   * @return the filter chain
   * @throws Exception exception
   */
  @Bean
  SecurityFilterChain filterChain(final HttpSecurity http) throws Exception {
    http.cors().configurationSource(corsConfiguration()).and()
        .authorizeRequests().anyRequest().anonymous().and().csrf().disable();
    return http.build();
  }
}
