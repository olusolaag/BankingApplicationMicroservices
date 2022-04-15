package com.learning.exception;

public class AccountCreationException extends RuntimeException {
	
	public AccountCreationException(String message) {
		super(message);
	}

	@Override
	public String toString() {
		return super.getMessage();
	}
	
}
