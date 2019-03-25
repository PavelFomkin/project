package com.historicalclub.error;

public class NoAccessException extends ResourceNotFoundException {

  public NoAccessException() {
    super("You have no access to the resource.");
  }
}
