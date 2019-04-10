package com.historicalclub.error;

public class TourNotFoundException extends ResourceNotFoundException {

  public TourNotFoundException(Long id) {
    super(String.format("Could not find tour %s", id));
  }
}
