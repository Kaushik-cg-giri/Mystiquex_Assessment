package com.movieflix.Exception;

public class MovieNotFoundException extends RuntimeException{

	public MovieNotFoundException(String message) {
		super(message);
	}

}
