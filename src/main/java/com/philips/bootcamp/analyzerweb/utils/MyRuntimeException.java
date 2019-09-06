package com.philips.bootcamp.analyzerweb.utils;

@SuppressWarnings("serial")
public class MyRuntimeException extends RuntimeException {
	  public MyRuntimeException(String message, Exception e) {
	    super(message, e);
	  }
	}
