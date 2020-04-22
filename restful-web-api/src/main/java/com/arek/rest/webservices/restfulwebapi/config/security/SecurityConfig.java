package com.arek.rest.webservices.restfulwebapi.config.security;

import com.arek.rest.webservices.restfulwebapi.service.UserDetailsServiceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static com.arek.rest.webservices.restfulwebapi.config.security.SecurityConstance.SIGN_UP_URL;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  UserDetailsServiceImp userDetailsService;
  BCryptPasswordEncoder bCryptPasswordEncoder;

  @Autowired
  public SecurityConfig(UserDetailsServiceImp userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
    this.userDetailsService = userDetailsService;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.cors().and().csrf().disable().authorizeRequests()
      .antMatchers(SIGN_UP_URL, "/login").permitAll()
      .anyRequest().authenticated()
      .and()
      .addFilter(new JWTAuthenticationFilter(authenticationManager()))
      .addFilter(new JWTAuthorizationFilter(authenticationManager()))
      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
  }


  @Bean
  CorsConfigurationSource corsConfigurationSource() {
    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration(".**", new CorsConfiguration().applyPermitDefaultValues());
    return source;
  }
}
