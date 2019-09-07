package com.sample.rest.exception;

public class FileNotFoundException extends RuntimeException {

  public FileNotFoundException() {
  }

  public FileNotFoundException(String s) {
    super(s);
  }

  public FileNotFoundException(String s, Throwable throwable) {
    super(s, throwable);
  }

  public FileNotFoundException(Throwable throwable) {
    super(throwable);
  }

  public FileNotFoundException(String s, Throwable throwable, boolean b, boolean b1) {
    super(s, throwable, b, b1);
  }
}
