package com.historicalclub.service;

import com.historicalclub.entity.Calendar;
import com.historicalclub.repository.CalendarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CalendarService {

  private final CalendarRepository calendarRepository;

  @Autowired
  public CalendarService(CalendarRepository calendarRepository) {
    this.calendarRepository = calendarRepository;
  }

  public List<Calendar> getVacantDates(Integer year, Integer month) {
    LocalDate.of(year, month, 1);

    Calendar date = Calendar.builder()
            .date(LocalDate.of(year, month, 1))
            .orderId(1L)
            .vacant(true)
            .build();
    calendarRepository.save(date);

    System.out.println("get vacant dates");
    return calendarRepository.findAll();
  }

//  public VacantDate getVacantDate(Long vacId) {
//    System.out.println("get vacant date " + vacId);
//    return vacantDateRepository.findByIdAndVacant(vacId, true)
//                               .orElseThrow(() -> new VacantDateNotFoundException(vacId));
//  }
//
//  public List<VacantDate> getAvailableVacantDates(Long id) {
//    System.out.println("get all available vacant dates");
//    Tour tour = tourService.getTour(id);
//    return vacantDateRepository.findAllByTourAndVacant(tour, true).orElse(Collections.emptyList());
//  }
//
//  public VacantDate updateVacantDate(Long vacId, VacantDate vacantDate) {
//    System.out.println("update vacant date " + vacId);
//    VacantDate currentVacantDate = vacantDateRepository.findById(vacId)
//                                                       .orElseThrow(() -> new VacantDateNotFoundException(vacId));
//    currentVacantDate.setStartDate(vacantDate.getStartDate());
//    currentVacantDate.setVacantPlaces(vacantDate.getVacantPlaces());
//    currentVacantDate.setVacant(vacantDate.getVacant());
//    return vacantDateRepository.save(currentVacantDate);
//  }
//
//  public VacantDate createVacantDate(VacantDate vacantDate) {
//    return vacantDateRepository.save(vacantDate);
//  }
//
//  public ResponseEntity<?> deleteVacantDate(Long vacId) {
//    System.out.println("delete vacant tour " + vacId);
//    VacantDate vacantDate = vacantDateRepository.findById(vacId).orElseThrow(() -> new VacantDateNotFoundException(vacId));
//    vacantDateRepository.delete(vacantDate);
//    System.out.println("deleted");
//    return ResponseEntity.ok().build();
//  }
//
//  public VacantDate changeStatusVacantDate(Long vacId) {
//    System.out.println("change status vacant tour " + vacId);
//    VacantDate vacantDate = vacantDateRepository.findById(vacId).orElseThrow(() -> new VacantDateNotFoundException(vacId));
//    vacantDate.setVacant(!vacantDate.getVacant());
//    return vacantDateRepository.save(vacantDate);
//  }
//
//  public VacantDate bookVacantDate(Long vacId){
//    System.out.println("change status vacant tour " + vacId);
//    VacantDate vacantDate = vacantDateRepository.findById(vacId).orElseThrow(() -> new VacantDateNotFoundException(vacId));
//    if(vacantDate.getVacant()){
//      vacantDate.setVacant(false);
//      return vacantDateRepository.save(vacantDate);
//    } else {
//      throw new VacantDateNotFoundException(vacId);
//    }
//  }
}
