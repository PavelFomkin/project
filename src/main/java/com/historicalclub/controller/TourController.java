package com.historicalclub.controller;

import com.historicalclub.entity.Tour;

import com.historicalclub.service.TourService;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TourController {

  @Autowired
  TourService tourService;

  @RequestMapping(value = "/tours", method = RequestMethod.GET)
  public List<Tour> getTours(HttpServletRequest request, @RequestHeader HttpHeaders headers) {
    return tourService.getTours();
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
