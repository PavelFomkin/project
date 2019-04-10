package com.historicalclub.error;

public class DisabledDateHasAlreadyExistException extends ResourceNotFoundException {

  public DisabledDateHasAlreadyExistException(String date) {
    super(String.format("Disabled date %s has already exist.", date));
  }
}
