package com.historicalclub.service;

import com.historicalclub.TourNotFoundException;
import com.historicalclub.entity.Tour;
import com.historicalclub.entity.VacantDate;
import com.historicalclub.repository.TourRepository;
import com.historicalclub.repository.VacantDateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class VacantDateService {

  @Autowired
  TourRepository tourRepository;

  @Autowired
  VacantDateRepository vacantDateRepository;

  public List<VacantDate> getVacantDates(Long id) {
    System.out.println("get vacant dates");
    Tour tour = tourRepository.findById(id).orElseThrow(() -> new TourNotFoundException(id));
    return vacantDateRepository.findAllByTour(tour);
  }

  public VacantDate getVacantDate(Long id) {
    System.out.println("get vacant date " + id);
    return vacantDateRepository.findById(id).orElseThrow(() -> new TourNotFoundException(id));
  }

  public List<VacantDate> getAvailableVacantDates(Long id) {
    System.out.println("get all available vacant dates");
    Tour tour = tourRepository.findById(id).orElseThrow(() -> new TourNotFoundException(id));
    return vacantDateRepository.findAllByTourAndVacant(tour, true);
  }

  public VacantDate updateVacantDate(Long vacId, VacantDate vacantDate) {
    System.out.println("update vacant tour " + vacId);
    VacantDate currentVacantDate = vacantDateRepository.findById(vacId).orElseThrow(() -> new TourNotFoundException(vacId));
    currentVacantDate.setStartDate(vacantDate.getStartDate());
    currentVacantDate.setVacantPlaces(vacantDate.getVacantPlaces());
    currentVacantDate.setVacant(vacantDate.getVacant());
    return vacantDateRepository.save(currentVacantDate);
  }

  public VacantDate createVacantDate(VacantDate vacantDate) {
    return vacantDateRepository.save(vacantDate);
  }

  public ResponseEntity<?> deleteVacantDate(Long vacId) {
    System.out.println("delete vacant tour " + vacId);
    VacantDate vacantDate = vacantDateRepository.findById(vacId).orElseThrow(() -> new TourNotFoundException(vacId));
    vacantDateRepository.delete(vacantDate);
    System.out.println("deleted");
    return ResponseEntity.ok().build();
  }

  public VacantDate changeStatusVacantDate(Long vacId) {
    System.out.println("change status vacant tour " + vacId);
    VacantDate vacantDate = vacantDateRepository.findById(vacId).orElseThrow(() -> new TourNotFoundException(vacId));
    vacantDate.setVacant(!vacantDate.getVacant());
    return vacantDateRepository.save(vacantDate);
  }
}
