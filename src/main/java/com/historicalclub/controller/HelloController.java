package com.historicalclub.controller;

import com.historicalclub.entity.Hero;
import com.historicalclub.entity.Tour;
import com.historicalclub.entity.VacationTour;
import com.historicalclub.repository.TourRepository;
import com.historicalclub.repository.VacationTourRepository;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HelloController {

  @Autowired
  TourRepository tourRepository;

  @Autowired
  VacationTourRepository vacationTourRepository;

  @RequestMapping("/")
  public String index() {
    return "Greetings from Spring Boot!";
  }

  @RequestMapping("/heroes")
  @CrossOrigin(origins = "http://localhost:4200")
  public List<Hero> greeting() {
    System.out.println("hey");
    List<Hero> list = new ArrayList<>();
    list.add(new Hero(1, "Hero1"));
    list.add(new Hero(2, "Hero2"));
    list.add(new Hero(3, "Hero3"));

    LocalDateTime date = LocalDateTime.now();
    Map<LocalDateTime, Integer> tours = new HashMap<>();
    tours.put(date, 2);
    tours.put(date.plusDays(1), 3);

    Tour tour = Tour.builder()
                    .name("name2")
                    .description("description")
                    .minNumberOfPlaces(1)
                    .maxNumberOfPlaces(5)
                    .price(1000)
                    .build();
    Tour tour1 = Tour.builder()
                     .name("name1")
                     .description("description1")
                     .minNumberOfPlaces(2)
                     .maxNumberOfPlaces(3)
                     .price(500)
                     .build();

    tourRepository.save(tour);
    tourRepository.save(tour1);

    VacationTour vacationTour = VacationTour.builder()
                                            .startTime(LocalDateTime.now())
                                            .numberOfPeople(5)
                                            .vacation(true)
                                            .tour(tour1).build();
    VacationTour vacationTour1 = VacationTour.builder()
                                             .startTime(LocalDateTime.now().plusDays(1))
                                             .numberOfPeople(5)
                                             .vacation(true)
                                             .tour(tour1).build();
    vacationTourRepository.save(vacationTour);
    vacationTourRepository.save(vacationTour1);

    return list;
  }

}
