package com.arek.rest.webservices.restfulwebapi.DTO;

import com.arek.rest.webservices.restfulwebapi.Data.ApiUser;
import com.arek.rest.webservices.restfulwebapi.service.UserServiceImp;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;

import org.springframework.stereotype.Component;

@Component
public class Initializer  implements ApplicationRunner {

  @Override
  public void run(ApplicationArguments args) throws Exception {
    UserServiceImp.registerUser(new ApiUser("arek", "demolka"));
    for (ApiUser user: UserServiceImp.users) {
      System.out.println("userName:" + user.getUsername());
      System.out.println("userPassword:" + user.getPassword());
    }
  }
}
