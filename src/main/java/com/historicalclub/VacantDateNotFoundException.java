package com.historicalclub;

public class VacantDateNotFoundException extends RuntimeException {

  public VacantDateNotFoundException(Long id) {
    super("Could not find vacant date " + id);
  }
}
