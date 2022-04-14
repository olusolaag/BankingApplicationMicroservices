package com.learning.exception;

public class NoDataFoundException extends RuntimeException {
	public NoDataFoundException(String message) {
		super(message);
	}

	@Override
	public String toString() {
		return super.getMessage();
	}
}
