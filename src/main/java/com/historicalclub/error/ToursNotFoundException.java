package com.historicalclub.error;

public class ToursNotFoundException extends ResourceNotFoundException {

  public ToursNotFoundException() {
    super("Could not find any tour");
  }
}
