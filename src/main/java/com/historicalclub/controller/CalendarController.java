package com.historicalclub.controller;

import com.historicalclub.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CalendarController {

  private final CalendarService calendarService;

  @Autowired
  public CalendarController(CalendarService calendarService) {
    this.calendarService = calendarService;
  }

  @RequestMapping(value = "/disabled-dates", method = RequestMethod.GET)
  public List<LocalDate> getDisabledDates() {
    return calendarService.getDisabledDates();
  }
}
