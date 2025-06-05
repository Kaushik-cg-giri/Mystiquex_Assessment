package com.movieflix.controller;

import java.io.IOException;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movieflix.Exception.FileNotFoundException;
import com.movieflix.dto.MovieDTO;
import com.movieflix.dto.MoviePageResponse;
import com.movieflix.service.MovieServiceImpl;
import com.movieflix.utils.AppConstants;

@RestController
@RequestMapping("api/v1/movie")
public class MovieController {
	
	@Autowired
	private MovieServiceImpl movieService;
	
	@PostMapping("/add")
	public ResponseEntity<MovieDTO> uploadMovie(@RequestPart MultipartFile file,@RequestPart String movieDTOObj) throws IOException {
		
		if(file.isEmpty())
			throw new FileNotFoundException("File is not uploaded");
		
		MovieDTO dto = convertToMovieDTO(movieDTOObj);
		return new ResponseEntity<>(movieService.uploadMovie(dto, file),HttpStatus.CREATED);
		
	}
	
	@GetMapping("/{movieName}")
	public ResponseEntity<MovieDTO>getMovie(@PathVariable String movieName){
		return new ResponseEntity<MovieDTO>(movieService.getMovie(movieName), HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<MovieDTO>> getAllMovies(){
		return ResponseEntity.ok(movieService.getAllMovie());
	}
	
	@PutMapping("/update/{movieId}")
	public ResponseEntity<MovieDTO> updateMovieHandler(@PathVariable Long movieId,@RequestPart(required = false) MultipartFile file,@RequestPart String movieDtoObj) throws IOException{
		MovieDTO obj = convertToMovieDTO(movieDtoObj);
		return new ResponseEntity<MovieDTO>(movieService.updateMovie(file,obj,movieId ),HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{movieId}")
	public ResponseEntity<String> deleteMovieHandler(@PathVariable Long movieId) throws IOException {
		return ResponseEntity.ok(movieService.deleteMovie(movieId));
	}
 	
	@GetMapping("/allMoviePage")
	public ResponseEntity<MoviePageResponse> handlerAllMoviePagination(@RequestParam(defaultValue = AppConstants.pageNumber, required = false) Integer pageNumber,
																	@RequestParam(defaultValue = AppConstants.pageSize,required = false) Integer pageSize){
		return ResponseEntity.ok(movieService.getAllMovieWihPagination(pageNumber, pageSize));
		
	}
	
	@GetMapping("/allMoviePageSorting")
	public ResponseEntity<MoviePageResponse> handlerAllMoviePaginationAndSorting(@RequestParam(defaultValue = AppConstants.pageNumber, required = false) Integer pageNumber,
																	@RequestParam(defaultValue = AppConstants.pageSize,required = false) Integer pageSize,
																	@RequestParam(defaultValue = AppConstants.sortBy, required = false) String sortBy,
																	@RequestParam(defaultValue = AppConstants.sortDir, required = false)String sortDir){
		return ResponseEntity.ok(movieService.getAllMoviesWithPaginatioAndSorting(pageNumber, pageSize,sortBy,sortDir));
		
	}

	private MovieDTO convertToMovieDTO(String movieDTOObj) throws JsonProcessingException {
		
		ObjectMapper mapper = new ObjectMapper();
		MovieDTO jsonObj =mapper.readValue(movieDTOObj, MovieDTO.class);;
		
		return jsonObj;
	}

}
