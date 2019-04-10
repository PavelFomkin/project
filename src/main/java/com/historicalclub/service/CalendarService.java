package com.historicalclub.service;

import com.historicalclub.entity.Calendar;
import com.historicalclub.error.DisabledDateHasAlreadyExistException;
import com.historicalclub.error.DisabledDateNotFoundException;
import com.historicalclub.repository.CalendarRepository;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CalendarService {

  private final CalendarRepository calendarRepository;

  @Autowired
  public CalendarService(CalendarRepository calendarRepository) {
    this.calendarRepository = calendarRepository;
  }

  public List<LocalDate> getDisabledDates() {
    System.out.println("get disabled dates");
    return calendarRepository.findAll().stream()
                                       .map(Calendar::getDate)
                                       .collect(Collectors.toList());
  }

  public LocalDate addDisabledDate(LocalDate date) {
    System.out.println("add disabled date");
    if (calendarRepository.findById(date).isPresent()) {
      throw new DisabledDateHasAlreadyExistException(date.format(DateTimeFormatter.ofPattern("d MMMM yyyy")));
    }
    Calendar calendar = new Calendar(date, null);
    return calendarRepository.save(calendar).getDate();
  }

  public Calendar addDisabledDate(Calendar calendar) {
    System.out.println("add disabled date");
    if (calendarRepository.findById(calendar.getDate()).isPresent()) {
      throw new DisabledDateHasAlreadyExistException(calendar.getDate().format(DateTimeFormatter.ofPattern("d MMMM yyyy")));
    }
    return calendarRepository.save(calendar);
  }

  public ResponseEntity<?> deleteDisabledDate(LocalDate date) {
    System.out.println("delete disabled date");
    Calendar cal = calendarRepository.findById(date).orElseThrow(() ->
        new DisabledDateNotFoundException(date.format(DateTimeFormatter.ofPattern("d MMMM yyyy"))));
    calendarRepository.delete(cal);
    return ResponseEntity.ok().build();
  }
}
