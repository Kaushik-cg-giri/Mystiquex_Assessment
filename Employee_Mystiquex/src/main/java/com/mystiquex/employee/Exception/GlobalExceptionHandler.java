package com.mystiquex.employee.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mystiquex.employee.employeeDAO.ErrorResponse;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	
	@ExceptionHandler(value = HttpMessageNotReadableException.class)
	public ResponseEntity<ErrorResponse> emptyBodyRequestExceptionHandler(HttpMessageNotReadableException ex){
		ErrorResponse response =new ErrorResponse(false,"Request Body is Empty");
		return new ResponseEntity<ErrorResponse>(response,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = ConstraintViolationException.class)
	public ResponseEntity<ErrorResponse> constraintViolationExceptionHandler(ConstraintViolationException ex){
		ErrorResponse response =new ErrorResponse(false,"Invalid Email Format");
		return new ResponseEntity<ErrorResponse>(response,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex){
		StringBuilder errors = new StringBuilder();
		for(FieldError error : ex.getBindingResult().getFieldErrors()) {
			errors.append(error.getDefaultMessage());
		}
		ErrorResponse response = new ErrorResponse(false,errors.toString());
		return new ResponseEntity<ErrorResponse>(response,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(EmployeesNotFoundException.class)
	public ResponseEntity<ErrorResponse> handlerEmployeeNotFound(EmployeesNotFoundException ex){
		
		ErrorResponse response = new ErrorResponse(false, ex.getMessage());
		
		return new ResponseEntity<ErrorResponse>(response, HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handlerException(Exception ex){
		
		ErrorResponse response = new ErrorResponse(false, "Something went wrong");
		
		return new ResponseEntity<ErrorResponse>(response, HttpStatus.NOT_FOUND);
		
	}
}
