package fr.sylvainjanet.test.backend;

import java.util.Arrays;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
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
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  // @Bean
  // public CorsConfigurationSource corsConfigurationSource() {
  // CorsConfiguration configuration = new CorsConfiguration();
  // configuration.setAllowedOrigins(Arrays.asList("*"));
  // configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE",
  // "OPTIONS"));
  // configuration.setAllowedHeaders(Arrays.asList("authorization", "content-type",
  // "x-auth-token"));
  // configuration.setExposedHeaders(Arrays.asList("x-auth-token"));
  // UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
  // source.registerCorsConfiguration("/**", configuration);
  //
  // return source;
  // }

  @Bean
  CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(Arrays.asList("http://127.0.0.1:5500",
        "https://sylvainjanet.fr", "https://dev.sylvainjanet.fr"));
    configuration.setAllowedMethods(Arrays.asList("GET", "POST"));
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    // http.csrf().disable().authorizeRequests().antMatchers("/**").permitAll();
    // http.authorizeRequests().anyRequest();
    // http.csrf().disable();
    // http.httpBasic().disable();
    http.cors();
  }

}
