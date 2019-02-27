package com.historicalclub.controller;

import com.historicalclub.entity.Tour;
import com.historicalclub.entity.VacantTour;

import com.historicalclub.service.TourService;
import java.util.*;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MainController {

  @Autowired
  TourService tourService;

  @RequestMapping("/")
  public String index() {
    return "Greetings from Spring Boot!";
  }

  @RequestMapping(value = "/tours", method = RequestMethod.GET)
  @CrossOrigin(origins = "http://localhost:4200")
  public List<Tour> getTours() {
    return tourService.getTours();
  }

  @RequestMapping(value = "/vacant-tours/{id}", method = RequestMethod.GET)
  @CrossOrigin(origins = "http://localhost:4200")
  public List<VacantTour> getAvailableVacantTours(@PathVariable Long id) {
    return tourService.getAvailableVacantTours(id);
  }

  @RequestMapping(value = "/vacant-tour/{id}", method = RequestMethod.GET)
  @CrossOrigin(origins = "http://localhost:4200")
  public VacantTour getVacantTour(@PathVariable Long id) {
    return tourService.getVacantTour(id);
  }

  @RequestMapping(value = "/all-vacant-tours/{id}", method = RequestMethod.GET)
  @CrossOrigin(origins = "http://localhost:4200")
  public List<VacantTour> getAllVacantTours(@PathVariable Long id) {
    return tourService.getVacantTours(id);
  }

  @RequestMapping(value = "/tour/{id}", method = RequestMethod.GET)
  @CrossOrigin(origins = "http://localhost:4200")
  public Tour getTour(@PathVariable Long id) {
    return tourService.getTour(id);
  }

  @RequestMapping(value = "/create-tour", method = RequestMethod.POST)
  @CrossOrigin(origins = "http://localhost:4200")
  public Tour createTour(@Valid @RequestBody Tour tour) {
    return tourService.createTour(tour);
  }

  @RequestMapping(value = "/update-tour/{id}", method = RequestMethod.PUT)
  @CrossOrigin(origins = "http://localhost:4200")
  public Tour updateTour(@PathVariable Long id, @Valid @RequestBody Tour tour) {
    return tourService.updateTour(id, tour);
  }

  @RequestMapping(value = "/delete-tour/{id}", method = RequestMethod.DELETE)
  @CrossOrigin(origins = "http://localhost:4200")
  public ResponseEntity<?> deleteTour(@PathVariable Long id) {
    return tourService.deleteTour(id);
  }
}
