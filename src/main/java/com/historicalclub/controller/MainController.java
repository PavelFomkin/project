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
@CrossOrigin(origins = "http://localhost:4200")
public class MainController {

  @Autowired
  TourService tourService;

  @RequestMapping("/")
  public String index() {
    return "Greetings from Spring Boot!";
  }

  @RequestMapping(value = "/tours", method = RequestMethod.GET)
  public List<Tour> getTours() {
    return tourService.getTours();
  }

  @RequestMapping(value = "/vacant-tours/{id}", method = RequestMethod.GET)
  public List<VacantTour> getAvailableVacantTours(@PathVariable Long id) {
    return tourService.getAvailableVacantTours(id);
  }

  @RequestMapping(value = "/vacant-tour/{id}", method = RequestMethod.GET)
  public VacantTour getVacantTour(@PathVariable Long id) {
    return tourService.getVacantTour(id);
  }

  @RequestMapping(value = "/all-vacant-tours/{id}", method = RequestMethod.GET)
  public List<VacantTour> getAllVacantTours(@PathVariable Long id) {
    return tourService.getVacantTours(id);
  }

  @RequestMapping(value = "/create-vacant-tour", method = RequestMethod.POST)
  public VacantTour createVacantTour(@Valid @RequestBody VacantTour vacantTour) {
    return tourService.createVacantTour(vacantTour);
  }

  @RequestMapping(value = "/delete-vacant-tour/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<?> deleteVacantTour(@PathVariable Long id) {
    return tourService.deleteVacantTour(id);
  }

  @RequestMapping(value = "/tour/{id}", method = RequestMethod.GET)
  public Tour getTour(@PathVariable Long id) {
    return tourService.getTour(id);
  }

  @RequestMapping(value = "/create-tour", method = RequestMethod.POST)
  public Tour createTour(@Valid @RequestBody Tour tour) {
    return tourService.createTour(tour);
  }

  @RequestMapping(value = "/update-tour/{id}", method = RequestMethod.PUT)
  public Tour updateTour(@PathVariable Long id, @Valid @RequestBody Tour tour) {
    return tourService.updateTour(id, tour);
  }

  @RequestMapping(value = "/delete-tour/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<?> deleteTour(@PathVariable Long id) {
    return tourService.deleteTour(id);
  }
}
