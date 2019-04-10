package com.historicalclub.controller;

import com.historicalclub.service.CalendarService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

  @RequestMapping(value = "/add-disabled-date", method = RequestMethod.PUT)
  public LocalDate addDisabledDate(@Valid @RequestBody LocalDate date) {
    return calendarService.addDisabledDate(date);
  }

  @RequestMapping(value = "/delete-disabled-date", method = RequestMethod.PUT)
  public ResponseEntity<?> deleteDisabledDate(@Valid @RequestBody LocalDate date) {
    return calendarService.deleteDisabledDate(date);
  }
}
