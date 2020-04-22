package com.arek.rest.webservices.restfulwebapi.service;

import com.arek.rest.webservices.restfulwebapi.Data.ApiUser;
import jdk.internal.dynalink.support.NameCodec;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImp {
  private static BCryptPasswordEncoder cryptPasswordEncoder;
  public UserServiceImp(BCryptPasswordEncoder cryptPasswordEncoder) {
    UserServiceImp.cryptPasswordEncoder = cryptPasswordEncoder;
  }

  public static List<ApiUser> users =new ArrayList<>();

  public static ApiUser getUserByName( String name){
    for (ApiUser user : users) {
      if (user.getUsername().equalsIgnoreCase(name))
        return user;
    }
    return null;
  }

  public static void registerUser (ApiUser apiUser ) {
    apiUser.setPassword(cryptPasswordEncoder.encode(apiUser.getPassword()));
    users.add(apiUser);
  }

}
