package com.historicalclub.service;

import com.historicalclub.error.VacantDateNotFoundException;
import com.historicalclub.error.VacantDatesNotFoundException;
import com.historicalclub.entity.Tour;
import com.historicalclub.entity.VacantDate;
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
    return vacantDateRepository.findAllByTour(tour)
                               .orElseThrow(() -> new VacantDatesNotFoundException(tour.getId()));
  }

  public VacantDate getVacantDate(Long vacId) {
    System.out.println("get vacant date " + vacId);
    return vacantDateRepository.findByIdAndVacant(vacId, true)
                               .orElseThrow(() -> new VacantDateNotFoundException(vacId));
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

  public VacantDate bookVacantDate(Long vacId){
    System.out.println("change status vacant tour " + vacId);
    VacantDate vacantDate = vacantDateRepository.findById(vacId).orElseThrow(() -> new VacantDateNotFoundException(vacId));
    if(vacantDate.getVacant()){
      vacantDate.setVacant(false);
      return vacantDateRepository.save(vacantDate);
    } else {
      throw new VacantDateNotFoundException(vacId);
    }
  }
}
