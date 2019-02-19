package com.historicalclub.controller;

import com.historicalclub.entity.Hero;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HelloController {

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @RequestMapping("/heroes")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<Hero> greeting() {
        System.out.println("hey");
        List<Hero> list = new ArrayList<>();
        list.add(new Hero(1,"Hero1"));
        list.add(new Hero(2,"Hero2"));
        list.add(new Hero(3,"Hero3"));

        return list;
    }

}
