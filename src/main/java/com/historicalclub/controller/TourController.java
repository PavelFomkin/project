package com.historicalclub.controller;

import com.historicalclub.entity.Tour;

import com.historicalclub.service.TourService;
import java.util.*;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TourController {

  private final TourService tourService;

  @Autowired
  public TourController(TourService tourService) {
    this.tourService = tourService;
  }

  @RequestMapping(value = "/available-tours", method = RequestMethod.GET)
  public List<Tour> getAvailableTours() {
    return tourService.getAvailableTours();
  }

  @RequestMapping(value = "/tours", method = RequestMethod.GET)
  public List<Tour> getTours() {
    return tourService.getTours();
  }

  @RequestMapping(value = "/available-tours/{id}", method = RequestMethod.GET)
  public Tour getAvailableTour(@PathVariable Long id) {
    return tourService.getTourIfAvailable(id);
  }

  @RequestMapping(value = "/tours/{id}", method = RequestMethod.GET)
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

  @RequestMapping(value = "/switch-tour-visibility/{id}", method = RequestMethod.GET)
  public Tour switchTourVisibility(@PathVariable Long id) {
    return tourService.switchTourVisibility(id);
  }
}
