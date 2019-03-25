package com.historicalclub.error;

public class VacantDatesNotFoundException extends ResourceNotFoundException {

  public VacantDatesNotFoundException(Long id) {
    super("Could not find vacant dates for tour " + id);
  }
}
