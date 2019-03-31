package com.historicalclub.controller;

import com.historicalclub.entity.Calendar;
import com.historicalclub.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CalendarController {

  private final CalendarService calendarService;

  @Autowired
  public CalendarController(CalendarService calendarService) {
    this.calendarService = calendarService;
  }

  @RequestMapping(value = "/vacant-dates", method = RequestMethod.GET)
  public List<Calendar> getVacantDates(@RequestParam String year, @RequestParam String month) {
    return calendarService.getVacantDates(Integer.parseInt(year), Integer.parseInt(month));
  }

//  @RequestMapping(value = "/vacant-date/{vacId}", method = RequestMethod.GET)
//  public VacantDate getVacantDate(@PathVariable Long vacId) {
//    return vacantDateService.getVacantDate(vacId);
//  }
//
//  @RequestMapping(value = "/all-vacant-dates/{id}", method = RequestMethod.GET)
//  public List<VacantDate> getVacantDates(@PathVariable Long id) {
//    return vacantDateService.getVacantDates(id);
//  }
//
//  @RequestMapping(value = "/create-vacant-date", method = RequestMethod.POST)
//  public VacantDate createVacantDate(@Valid @RequestBody VacantDate vacantDate) {
//    return vacantDateService.createVacantDate(vacantDate);
//  }
//
//  @RequestMapping(value = "/update-vacant-date/{vacId}", method = RequestMethod.PUT)
//  public VacantDate updateVacantDate(@PathVariable Long vacId, @Valid @RequestBody VacantDate vacantDate) {
//    return vacantDateService.updateVacantDate(vacId, vacantDate);
//  }
//
//  @RequestMapping(value = "/delete-vacant-date/{vacId}", method = RequestMethod.DELETE)
//  public ResponseEntity<?> deleteVacantDate(@PathVariable Long vacId) {
//    return vacantDateService.deleteVacantDate(vacId);
//  }
//
//  @RequestMapping(value = "/change-status-vacant-date/{vacId}", method = RequestMethod.GET)
//  public VacantDate changeStatusVacantDate(@PathVariable Long vacId) {
//    return vacantDateService.changeStatusVacantDate(vacId);
//  }
}
