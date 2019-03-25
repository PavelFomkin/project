package com.historicalclub.service;

import com.historicalclub.TourNotFoundException;
import com.historicalclub.VacantDateNotFoundException;
import com.historicalclub.VacantDatesNotFoundException;
import com.historicalclub.entity.Tour;
import com.historicalclub.entity.VacantDate;
import com.historicalclub.repository.TourRepository;
import com.historicalclub.repository.VacantDateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VacantDateService {

  @Autowired
  private VacantDateRepository vacantDateRepository;

  @Autowired
  private TourService tourService;

  public List<VacantDate> getVacantDates(Long id) {
    System.out.println("get vacant dates");
    Tour tour = tourService.getTour(id);
    return getVacantDates(tour);
  }


  public List<VacantDate> getVacantDates(Tour tour){
    return vacantDateRepository.findAllByTour(tour)
                               .orElseThrow(() -> new VacantDatesNotFoundException(tour.getId()));
  }

  public VacantDate getVacantDate(Long id) {
    System.out.println("get vacant date " + id);
    return vacantDateRepository.findById(id).orElseThrow(() -> new VacantDateNotFoundException(id));
  }

  public List<VacantDate> getAvailableVacantDates(Long id) {
    System.out.println("get all available vacant dates");
    Tour tour = tourService.getTour(id);
    return vacantDateRepository.findAllByTourAndVacant(tour, true)
                               .orElseThrow(() -> new VacantDatesNotFoundException(id));
  }

  public VacantDate updateVacantDate(Long vacId, VacantDate vacantDate) {
    System.out.println("update vacant date " + vacId);
    VacantDate currentVacantDate = vacantDateRepository.findById(vacId)
                                                       .orElseThrow(() -> new VacantDateNotFoundException(vacId));
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
    VacantDate vacantDate = vacantDateRepository.findById(vacId).orElseThrow(() -> new VacantDateNotFoundException(vacId));
    vacantDateRepository.delete(vacantDate);
    System.out.println("deleted");
    return ResponseEntity.ok().build();
  }

  public VacantDate changeStatusVacantDate(Long vacId) {
    System.out.println("change status vacant tour " + vacId);
    VacantDate vacantDate = vacantDateRepository.findById(vacId).orElseThrow(() -> new VacantDateNotFoundException(vacId));
    vacantDate.setVacant(!vacantDate.getVacant());
    return vacantDateRepository.save(vacantDate);
  }

  public void bookVacantDate(Long vacId){
    System.out.println("change status vacant tour " + vacId);
    VacantDate vacantDate = vacantDateRepository.findById(vacId).orElseThrow(() -> new VacantDateNotFoundException(vacId));
    if(vacantDate.getVacant()){
      vacantDate.setVacant(false);
      vacantDateRepository.save(vacantDate);
    } else {
      throw new TourNotFoundException(vacId);
    }
  }
}
