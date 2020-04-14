package com.arek.rest.webservices.restfulwebapi.controllers;

import com.arek.rest.webservices.restfulwebapi.beans.BasicWelcomeBean;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class WelcomeController {

    @GetMapping("/welcome")
    public BasicWelcomeBean getWelcomeMessage() {
        throw new RuntimeException("Some Error has Happened! Contact Support **-***");
       // return new BasicWelcomeBean("Welcome visitor in my todos app");
    }

    @GetMapping("/welcome/{name}")
    public BasicWelcomeBean getWelcomeMessagePathVariable(@PathVariable String name) {
//        throw new RuntimeException("Some Error has Happened! Contact Support **-***");
        return new BasicWelcomeBean(String.format("Welcome %s in my todos app", name));
    }

}
