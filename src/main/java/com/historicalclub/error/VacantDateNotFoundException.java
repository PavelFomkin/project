package com.historicalclub.error;

public class VacantDateNotFoundException extends ResourceNotFoundException {

  public VacantDateNotFoundException(Long id) {
    super("Could not find vacant date " + id);
  }
}
