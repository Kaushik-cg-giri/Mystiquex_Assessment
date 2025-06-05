package com.movieflix.service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.movieflix.Exception.FileNotFoundException;
import com.movieflix.Exception.MovieNotFoundException;
import com.movieflix.dto.MovieDTO;
import com.movieflix.dto.MoviePageResponse;
import com.movieflix.entities.Movie;
import com.movieflix.repository.MovieRepository;

@Service
public class MovieServiceImpl implements MovieInterface{
	
	@Autowired
	private MovieRepository movieRpo;
	
	@Autowired
	private FileService fileService;
	
	@Value("${project.poster}")
	private String path;
	
	@Value("${base.url}")
	private String baseURL;

	@Override
	public MovieDTO uploadMovie(MovieDTO movieDTO, MultipartFile file) throws IOException {
		
		if(Files.exists(Paths.get(path+File.separator+file.getOriginalFilename())))
				throw new FileNotFoundException("File Not Present ");

		//get poster name
		String fileName = file.getOriginalFilename();
		
		//upload file to poster folder
		String uploadFileName = fileService.uploadFile(path, file);
		
		//set poster name in movieDTO response
		movieDTO.setPoster(uploadFileName);
		
		//create new movie object
		//store in RDB
		Movie movieObj = new Movie(0,
									movieDTO.getMovie_name(),
									movieDTO.getRating(),
									movieDTO.getreleaseDate(),
									movieDTO.getmovieArtists(),
									movieDTO.getPoster()
									);
		
		//generate poster url
		String posterURL = baseURL+"/file/"+fileName;
		
		//save movie object into db
		movieRpo.save(movieObj);
				
		//develop the response
		MovieDTO response = new MovieDTO(movieObj.getmovieId(),
										movieObj.getmovieName(),
										movieObj.getRating(), 
										movieObj.getreleaseDate(),
										movieObj.getmovieArtists(),
										movieObj.getPoster(), 
										posterURL);
		
		
		
		
		
		return response;
	}

	@Override
	public MovieDTO getMovie(String movieName) {
		
		//fetch movie using movie name
		Movie movieObj = movieRpo.findByMovieName(movieName).orElseThrow(()-> new MovieNotFoundException(movieName+" Movie Not Present "));
		
		// fetch movie url
		String posterURL =  baseURL+"/file/"+movieObj.getPoster();
		
		MovieDTO response = new MovieDTO(
										 movieObj.getmovieId(),
										 movieObj.getmovieName(),
										 movieObj.getRating(),
										 movieObj.getreleaseDate(),
										 movieObj.getmovieArtists(),
										 movieObj.getPoster(), 
										 posterURL);
		
		return response;
	}

	@Override
	public List<MovieDTO> getAllMovie() {
		
		//retrive all the movies from db
		List<Movie> movieList = movieRpo.findAll();
		List<MovieDTO> movies = new ArrayList<MovieDTO>();
		
		  //Check movie presen or not 
		if(movieList.isEmpty()) { 
			throw new MovieNotFoundException("No Movie Present in DB");
			}
		 
		for(Movie movie :movieList) {
			// iterate each poster URL 
			String posterURL = baseURL+"/file/"+movie.getPoster();
			
			//set each movie response
			MovieDTO response =new MovieDTO(
											movie.getmovieId(),
											movie.getmovieName(),
											movie.getRating(),
											movie.getreleaseDate(),
											movie.getmovieArtists(),
											movie.getPoster(),
											posterURL);
			//add each movie response object to list
			movies.add(response);
		}
		return movies;
	}

	@Override
	public MovieDTO updateMovie(MultipartFile file, MovieDTO movieDto, long movieId) throws IOException {
		
		 // 1. check if movie object exists with given movieId
		Movie movie = movieRpo.findById(movieId).orElseThrow(()->new MovieNotFoundException(movieId+" Movie Not Present "));
		
		
		 // 2. if file is null, do nothing
        // if file is not null, then delete existing file associated with the record,
        // and upload the new file
		
		String uploadFileName = movieDto.getPoster();
		
		if(file!=null) {
			Files.deleteIfExists(Paths.get(path+File.separator+movie.getPoster()));
			uploadFileName = fileService.uploadFile(path,file);
		}
      
		// 3. set movieDto's poster value, according to step2
        movieDto.setPoster(uploadFileName);
        
     // 4. map it to Movie object
		Movie movieObj  = new Movie(movieId, 
								movieDto.getMovie_name(), 
								movieDto.getRating(),
								movieDto.getreleaseDate(),
								movieDto.getmovieArtists(),
								movieDto.getPoster());
		
		// 5. save the movie object -> return saved movie object
		Movie savedObj = movieRpo.save(movieObj);
		
		// 6. generate posterUrl for it
		String posterURL = baseURL+"/file/"+uploadFileName;
		
		// 7. map to MovieDto and return it
		MovieDTO response =new MovieDTO(savedObj.getmovieId(),
				savedObj.getmovieName(),
				savedObj.getRating(),
				savedObj.getreleaseDate(),
				savedObj.getmovieArtists(),
				savedObj.getPoster(),
				posterURL);
		
		return response;
	}

	@Override
	public String deleteMovie(Long movieId) throws IOException {
		
		 // 1. check if movie object exists with given movieId
		Movie movie = movieRpo.findById(movieId).orElseThrow(()->new MovieNotFoundException(movieId+" Movie Not Present "));
		
		String movieName = movie.getmovieName();
		
		// 2. delete the file associated with this object
		Files.deleteIfExists(Paths.get(path+File.separator+movie.getPoster()));
		
		// 3. delete the movie object
		movieRpo.deleteById(movieId);
		
		
		return  movieName+"Following Movie deleted from DB ";
	}

	@Override
	public MoviePageResponse getAllMovieWihPagination(Integer pageNumber, Integer pageSize) {
		
		List<MovieDTO> movies = new ArrayList<MovieDTO>();

		//1.Pagaeable interface used for pagination , passing pagenumber and page size as argument
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		
		//2. Get all movie in page format
		Page<Movie> moviePages = movieRpo.findAll(pageable);
		
		//3.map content in page to movieDTO
		List<Movie> movieDTOs = moviePages.getContent();
		
		  //Check movie presen or not 
		if(movieDTOs.isEmpty()) { 
			throw new MovieNotFoundException("No Movie Present in DB");
			}
		 
		for(Movie movie :movieDTOs) {
			// iterate each poster URL 
			String posterURL = baseURL+"/file/"+movie.getPoster();
			
			//set each movie response
			MovieDTO response =new MovieDTO(
											movie.getmovieId(),
											movie.getmovieName(),
											movie.getRating(),
											movie.getreleaseDate(),
											movie.getmovieArtists(),
											movie.getPoster(),
											posterURL);
			//add each movie response object to list
			movies.add(response);
		}

		return new MoviePageResponse(movies,
								pageNumber,
								pageSize,
								moviePages.getTotalElements(),
								moviePages.getTotalPages(),
								moviePages.isLast());
	}

	@Override
	public MoviePageResponse getAllMoviesWithPaginatioAndSorting(Integer pageNumber, Integer pageSize, String sortBy,
			String sortDir) {

		List<MovieDTO> movies = new ArrayList<MovieDTO>();

		//1.create sor object it defines sort according to which field and sort direction 
		Sort sort = sortDir.equalsIgnoreCase("asc") ?  Sort.by(sortBy).ascending()
													:Sort.by(sortBy).descending();
		
		//2.create pagination information //PageRequest used for providing implementation of pagination
		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
		
		//3.Page sublist containing entire list
		Page<Movie> moviePages = movieRpo.findAll(pageable);
		
		//4. Map page content to Movie object
		List<Movie> movieDtos = moviePages.getContent();
		
		//Check movie presen or not 
				if(movieDtos.isEmpty()) { 
					throw new MovieNotFoundException("No Movie Present in DB");
					}
				 
				for(Movie movie :movieDtos) {
					// iterate each poster URL 
					String posterURL = baseURL+"/file/"+movie.getPoster();
					
					//set each movie response
					MovieDTO response =new MovieDTO(
													movie.getmovieId(),
													movie.getmovieName(),
													movie.getRating(),
													movie.getreleaseDate(),
													movie.getmovieArtists(),
													movie.getPoster(),
													posterURL);
					//add each movie response object to list
					movies.add(response);
				}

				return new MoviePageResponse(movies,
										pageNumber,
										pageSize,
										moviePages.getTotalElements(),
										moviePages.getTotalPages(),
										moviePages.isLast());
			}
	


}
