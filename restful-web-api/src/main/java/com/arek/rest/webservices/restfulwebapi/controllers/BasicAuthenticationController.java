package com.arek.rest.webservices.restfulwebapi.controllers;

import com.arek.rest.webservices.restfulwebapi.beans.AuthenticationBean;
import com.arek.rest.webservices.restfulwebapi.beans.BasicWelcomeBean;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BasicAuthenticationController {

  @PostMapping("/basicauthj")
  public AuthenticationBean getAuthMessage() {
//        throw new RuntimeException("Some Error has Happened! Contact Support **-***");
    return new AuthenticationBean("You are authenticated");

  }
}
