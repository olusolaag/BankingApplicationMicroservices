package com.learning.advice;



import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.learning.exception.ApiError;
import com.learning.exception.IdNotFoundException;
import com.learning.exception.NoDataFoundException;
import com.learning.exception.UserAlreadyExistsException;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice extends ResponseEntityExceptionHandler{	
	
	@ExceptionHandler(UserAlreadyExistsException.class)
	public ResponseEntity<?> nameAlreadyExistsException(UserAlreadyExistsException e){
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "user already exists", e);
		return buildResponseEntity(apiError);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ApiError apiError = new ApiError(status);
		apiError.addValidationErrors(ex.getFieldErrors());
		apiError.addValidationObjectErrors(ex.getBindingResult().getGlobalErrors());

		return buildResponseEntity(apiError);
	}
	
	private ResponseEntity<Object> buildResponseEntity(ApiError apiError){
		return new ResponseEntity<Object>(apiError, apiError.getStatus());
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	protected ResponseEntity<?> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException e){
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
		apiError.setMessage(e.getMessage());
		return buildResponseEntity(apiError);
		
	}
	@ExceptionHandler(ConstraintViolationException.class)
	protected ResponseEntity<?> handleMethodConstraintViolationException(ConstraintViolationException e){
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
		apiError.setMessage(e.getMessage());
		return buildResponseEntity(apiError);		
	}
	
	@ExceptionHandler(IdNotFoundException.class)
	protected ResponseEntity<?> handleMethodIdNotFound(IdNotFoundException e){
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
		apiError.setMessage(e.getMessage());
		return buildResponseEntity(apiError);
	}
	@ExceptionHandler(NoDataFoundException.class)
	protected ResponseEntity<?> handleMethodNoDataFoundException(NoDataFoundException e){
		ApiError apiError = new ApiError(HttpStatus.NOT_FOUND);
		apiError.setMessage(e.getMessage());
		return buildResponseEntity(apiError);
	}
//	@ExceptionHandler(Exception.class)
//	protected ResponseEntity<?> handleMethodException(Exception e){
//		ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR);
//		apiError.setMessage(e.getMessage());
//		return buildResponseEntity(apiError);
//	}


	
	
}
