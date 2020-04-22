package com.arek.rest.webservices.restfulwebapi.Data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiUser {
  private String username;
  private String password;
}
