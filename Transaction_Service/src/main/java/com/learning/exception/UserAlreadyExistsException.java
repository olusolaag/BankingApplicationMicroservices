package com.learning.exception;

public class UserAlreadyExistsException extends Exception{


	public UserAlreadyExistsException(String message) {
		super(message);
	}

	@Override
	public String toString() {
		return super.getMessage();
	}
	
}
