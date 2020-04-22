package com.arek.rest.webservices.restfulwebapi.service;

import com.arek.rest.webservices.restfulwebapi.Data.ApiUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsServiceImp implements UserDetailsService {
 private UserServiceImp userServiceImp;

 @Autowired
  public UserDetailsServiceImp(UserServiceImp userServiceImp) {
    this.userServiceImp = userServiceImp;
  }

  @Override
  public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
    ApiUser apiUser = userServiceImp.getUserByName(s);
    if (apiUser == null) {
      throw new  UsernameNotFoundException(s);
    }
    return new User(apiUser.getUsername(), apiUser.getPassword(), Collections.emptyList())
    ;

  }
}
