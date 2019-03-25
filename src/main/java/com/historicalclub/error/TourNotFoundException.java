package com.historicalclub.error;

public class TourNotFoundException extends ResourceNotFoundException {

  public TourNotFoundException(Long id) {
    super("Could not find tour " + id);
  }
}
