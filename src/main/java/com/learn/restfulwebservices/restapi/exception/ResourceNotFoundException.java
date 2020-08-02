package com.learn.restfulwebservices.restapi.exception;

public class ResourceNotFoundException extends RuntimeException {
  public ResourceNotFoundException(String exception) {
    super(exception);
  }
}
