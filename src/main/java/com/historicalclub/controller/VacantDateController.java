package com.historicalclub.controller;

import com.historicalclub.entity.VacantDate;
import com.historicalclub.service.VacantDateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class VacantDateController {

  @Autowired
  VacantDateService vacantDateService;

  @RequestMapping(value = "/vacant-dates/{id}", method = RequestMethod.GET)
  public List<VacantDate> getAvailableVacantDates(@PathVariable Long id) {
    return vacantDateService.getAvailableVacantDates(id);
  }

  @RequestMapping(value = "/vacant-date/{vacId}", method = RequestMethod.GET)
  public VacantDate getVacantDate(@PathVariable Long vacId) {
    return vacantDateService.getVacantDate(vacId);
  }

  @RequestMapping(value = "/all-vacant-dates/{id}", method = RequestMethod.GET)
  public List<VacantDate> getAllVacantDates(@PathVariable Long id) {
    return vacantDateService.getVacantDates(id);
  }

  @RequestMapping(value = "/create-vacant-date", method = RequestMethod.POST)
  public VacantDate createVacantDate(@Valid @RequestBody VacantDate vacantDate) {
    return vacantDateService.createVacantDate(vacantDate);
  }

  @RequestMapping(value = "/update-vacant-date/{vacId}", method = RequestMethod.PUT)
  public VacantDate updateVacantDate(@PathVariable Long vacId, @Valid @RequestBody VacantDate vacantDate) {
    return vacantDateService.updateVacantDate(vacId, vacantDate);
  }

  @RequestMapping(value = "/delete-vacant-date/{vacId}", method = RequestMethod.DELETE)
  public ResponseEntity<?> deleteVacantDate(@PathVariable Long vacId) {
    return vacantDateService.deleteVacantDate(vacId);
  }

  @RequestMapping(value = "/change-status-vacant-date/{vacId}", method = RequestMethod.GET)
  public VacantDate changeStatusVacantDate(@PathVariable Long vacId) {
    return vacantDateService.changeStatusVacantDate(vacId);
  }
}
