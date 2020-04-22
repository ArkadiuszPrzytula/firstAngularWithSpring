package com.arek.rest.webservices.restfulwebapi.config.security;

import com.auth0.jwt.JWT;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static com.arek.rest.webservices.restfulwebapi.config.security.SecurityConstance.EXPIRATION_TIME;
import static com.arek.rest.webservices.restfulwebapi.config.security.SecurityConstance.SECRET;

@Service
public class JwsUtil {
  public String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);


  }

  public Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }

  private Claims extractAllClaims(String token) {
    return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
  }


  public String generateToken(UserDetails userDetails) {
    Map<String, Object> claims = new HashMap<>();
    return createToken(claims, userDetails.getUsername());
  }

  private String createToken(Map<String, Object> claims, String username) {
    return Jwts.builder()
      .setClaims(claims)
      .setSubject(username)
      .setIssuedAt(new Date(System.currentTimeMillis()))
      .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
      .signWith(SignatureAlgorithm.ES256, SECRET).compact();
  }

}
