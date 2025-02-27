package com.arek.rest.webservices.restfulwebapi.config.security;

import com.arek.rest.webservices.restfulwebapi.Data.ApiUser;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.arek.rest.webservices.restfulwebapi.config.security.SecurityConstance.*;

import org.springframework.security.core.userdetails.User;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;


public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
  private AuthenticationManager authenticationManager;

  public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
    this.authenticationManager = authenticationManager;
  }


  @Override
  public Authentication attemptAuthentication(HttpServletRequest request,
                                              HttpServletResponse response) throws AuthenticationException {

    try {
      ApiUser creds = new ObjectMapper().readValue(request.getInputStream(), ApiUser.class);
      return authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
          creds.getUsername(),
          creds.getPassword(),
          new ArrayList<>())
      );
    } catch (IOException e) {
      throw new RuntimeException(e);

    }
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest request,
                                          HttpServletResponse response,
                                          FilterChain chain,
                                          Authentication authResult) throws IOException, ServletException {


    String token = JWT.create().withSubject(((User) authResult.getPrincipal()).getUsername())
      .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
      .sign(Algorithm.HMAC256(SECRET.getBytes()));
    response.addHeader(HEADER_STRING, TOKEN_PREFIX + token);

  }
}
