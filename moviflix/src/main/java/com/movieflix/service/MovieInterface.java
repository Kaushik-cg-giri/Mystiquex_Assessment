package com.movieflix.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.movieflix.dto.MovieDTO;
import com.movieflix.dto.MoviePageResponse;
import com.movieflix.entities.Movie;

public interface MovieInterface {
	
	MovieDTO uploadMovie(MovieDTO movieDTO,MultipartFile fileName)throws IOException;     
	
	MovieDTO getMovie(String movieName);
	
	List<MovieDTO> getAllMovie();
	
	MovieDTO updateMovie(MultipartFile file,MovieDTO movieDto,long movieId)throws IOException;
	
	String deleteMovie(Long movieId)throws IOException;
	
	MoviePageResponse getAllMovieWihPagination(Integer pageNumber,Integer pageSize);
	
	MoviePageResponse getAllMoviesWithPaginatioAndSorting(Integer pageNuInteger,Integer pageSize,String sortBy,String sortDir);
	

}
