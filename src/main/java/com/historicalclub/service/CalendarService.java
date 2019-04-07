package com.historicalclub.service;

import com.historicalclub.entity.Calendar;
import com.historicalclub.repository.CalendarRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
}
