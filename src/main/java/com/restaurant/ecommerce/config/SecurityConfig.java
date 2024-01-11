package com.restaurant.ecommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity httpSec) throws Exception {
    return httpSec
            .authorizeHttpRequests(auth -> {
              auth.requestMatchers("/api/v1/users/register", "/api/v1/pizzas", "/api/v1/users/form").permitAll();
              auth.anyRequest().authenticated();
            })
            .oauth2Login(withDefaults())
            .build();
  }

  @Bean
  CorsConfiguration corsConfiguration() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(List.of("http://localhost:5843"));
    configuration.setAllowedHeaders(List.of("*"));
    configuration.setAllowedMethods(List.of("*"));
    configuration.setAllowCredentials(true);
    UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
    urlBasedCorsConfigurationSource.registerCorsConfiguration("/", configuration);
    return configuration;
  }

}