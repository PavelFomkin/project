package com.historicalclub.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class MainController {

  @RequestMapping("/")
  public String index() {
    return "Greetings from Spring Boot!";
  }
}
