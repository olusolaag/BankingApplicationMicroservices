package com.cogent.customer_service.exception;

public class CustomerAlreadyExistsException extends Exception{


	public CustomerAlreadyExistsException(String message) {
		super(message);
	}

	@Override
	public String toString() {
		return super.getMessage();
	}
	
}
