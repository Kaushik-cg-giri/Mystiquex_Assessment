package com.movieflix.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(FileNotFoundException.class)
	public ProblemDetail handlerFileNotFoundException(FileNotFoundException ex) {
		
		return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());	
	}
	
	@ExceptionHandler(MovieNotFoundException.class)
	public ProblemDetail handlerMovieNotFoundException(MovieNotFoundException ex) {
		return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
	}

}
