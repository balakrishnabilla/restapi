package com.learn.restfulwebservices.restapi.exception;

public class VacationNotFoundException extends RuntimeException {
  public VacationNotFoundException(String exception) {
    super(exception);
  }
}
