package com.historicalclub;

public class TourNotFoundException extends RuntimeException {

  public TourNotFoundException(Long id) {
    super("Could not find tour " + id);
  }
}
