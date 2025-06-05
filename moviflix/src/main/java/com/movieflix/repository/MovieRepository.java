package com.movieflix.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movieflix.entities.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {
	
	Optional<Movie> findByMovieName(String movieName);

}
