package com.arek.rest.webservices.restfulwebapi.config.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import static com.arek.rest.webservices.restfulwebapi.config.security.SecurityConstance.*;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

  public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
    super(authenticationManager);
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request,
                                  HttpServletResponse response,
                                  FilterChain chain) throws IOException, ServletException {

    String header = request.getHeader(HEADER_STRING);

    if (header == null || !header.startsWith(TOKEN_PREFIX)) {
      System.out.println(header);
      chain.doFilter(request, response);
      return;
    }
    UsernamePasswordAuthenticationToken authentication = getAuthentication(request);

    SecurityContextHolder.getContext().setAuthentication(authentication);
    chain.doFilter(request, response);
  }

  private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest req) {
    String token = req.getHeader(HEADER_STRING);
    System.out.println("dddddd");

    if (token != null) {
      //parse the token
      String user = JWT.require(Algorithm.HMAC256(SECRET.getBytes()))
        .build()
        .verify(token.replace(TOKEN_PREFIX, ""))
        .getSubject();

      System.out.println(user);
      if (user != null) {
        return new UsernamePasswordAuthenticationToken(user,
          null,
          new ArrayList<>());
      }
      return null;
    }
    return null;

  }
}
