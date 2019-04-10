package com.historicalclub.error;

public class DisabledDateNotFoundException extends ResourceNotFoundException {

  public DisabledDateNotFoundException(String date) {
    super(String.format("Disabled date %s not found.", date));
  }
}
