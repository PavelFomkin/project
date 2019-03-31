package com.historicalclub.service;

import com.historicalclub.error.NoAccessException;
import com.historicalclub.error.TourNotFoundException;
import com.historicalclub.error.ToursNotFoundException;
import com.historicalclub.entity.Tour;
import com.historicalclub.repository.TourRepository;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TourService {

  private final TourRepository tourRepository;

  @Autowired
  public TourService(TourRepository tourRepository) {
    this.tourRepository = tourRepository;
  }

  public List<Tour> getAvailableTours(){
    return tourRepository.getPreviewAvailableTours().stream()
            .map(this::mapObjToPreviewTour)
            .collect(Collectors.toList());
  }

  public List<Tour> getTours() {
    System.out.println("show tours");
    return tourRepository.getPreviewTours().stream()
            .map(this::mapObjToPreviewTour)
            .collect(Collectors.toList());
  }

  public Tour getTour(Long id) {
    System.out.println("get tour " + id);
    return tourRepository.findById(id).orElseThrow(() -> new TourNotFoundException(id));
  }

  public Tour getTourIfAvailable(Long id) {
    System.out.println("get tour " + id);
    Tour tour = tourRepository.findById(id).orElseThrow(() -> new TourNotFoundException(id));
    if(tour.getVisible()){
      return tour;
    } else {
      throw new NoAccessException();
    }
  }

  public Tour updateTour(Long id, Tour tour) {
    System.out.println("update tour " + id);
    Tour currentTour = tourRepository.findById(id).orElseThrow(() -> new TourNotFoundException(id));
    currentTour.setTitle(tour.getTitle());
    currentTour.setShortDescription(tour.getShortDescription());
    currentTour.setDescription(tour.getDescription());
    currentTour.setDuration(tour.getDuration());
    currentTour.setVenue(tour.getVenue());
    currentTour.setParticipants(tour.getParticipants());
    currentTour.setPrice(tour.getPrice());
    currentTour.setImageUrl(tour.getImageUrl());
    currentTour.setPictures(tour.getPictures());
    return tourRepository.save(currentTour);
  }

  public Tour createTour(Tour tour) {
    System.out.println("create tour");
    return tourRepository.save(tour);
  }

  public ResponseEntity<?> deleteTour(Long id) {
    System.out.println("delete tour " + id);
    Tour tour = tourRepository.findById(id).orElseThrow(() -> new TourNotFoundException(id));
    tourRepository.delete(tour);
    System.out.println("deleted");
    return ResponseEntity.ok().build();
  }

  public Tour switchTourVisibility(Long id) {
    System.out.println("switch visibility for tour " + id);
    Tour tour = tourRepository.findById(id).orElseThrow(() -> new TourNotFoundException(id));
    tour.setVisible(!tour.getVisible());
    return tourRepository.save(tour);
  }

  private Tour mapObjToPreviewTour(Object data){
    Object[] columns = (Object[]) data;
    Tour tour = new Tour();
    tour.setId(((BigInteger) columns[0]).longValue());
    tour.setTitle((String) columns[1]);
    tour.setShortDescription((String) columns[2]);
    tour.setImageUrl((String) columns[3]);
    tour.setVisible((Boolean) columns[4]);
    return tour;
  }
}
