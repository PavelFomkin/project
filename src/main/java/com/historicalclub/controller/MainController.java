package com.historicalclub.controller;

import com.historicalclub.entity.User;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class MainController {

  @RequestMapping("/")
  public String index() {
    return "Greetings from Spring Boot!";
  }

  @RequestMapping(value = "/login", method = RequestMethod.POST)
  public String loginAuth(@Valid @RequestBody User user) {
    if ("admin".equals(user.getUsername()) && "password".equals(user.getPassword())) {
      return "True";
    }
    return null;
  }
}
