package com.historicalclub;

public class ToursNotFoundException extends RuntimeException {

  public ToursNotFoundException() {
    super("Could not find any tour");
  }
}
