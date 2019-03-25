package com.historicalclub;

public class VacantDatesNotFoundException extends RuntimeException {

  public VacantDatesNotFoundException(Long id) {
    super("Could not find vacant dates for tour " + id);
  }
}
