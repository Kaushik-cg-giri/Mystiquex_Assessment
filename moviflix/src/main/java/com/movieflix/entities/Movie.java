package com.movieflix.entities;

import java.time.LocalDate;
import java.util.Set;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "movie")
public class Movie {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long movieId;
	
	@Column(nullable = false,length = 200)
	@NotBlank(message = "please provide movie name..!")
	private String movieName;
	
	@Column(nullable = false)
	@NonNull
	private float rating;
	
	@Column(nullable = false,length = 200)
	@NotBlank(message = "please provide movie release date..!")
	private String releaseDate;
	
	@ElementCollection
	@CollectionTable
	private Set<String> movieArtists;
	
	@Column(nullable = false,length = 200)
	@NotBlank(message = "please provide movie poster..!")
	private String poster;

	public Movie() {
		super();
	}

	public Movie(long movieId, @NotBlank(message = "please provide movie name..!") String movieName,
			@NotNull float rating,
			@NotBlank(message = "please provide movie release date..!") String releaseDate,
			Set<String> movieArtists, @NotBlank(message = "please provide movie poster..!") String poster) {
		super();
		this.movieId = movieId;
		this.movieName = movieName;
		this.rating = rating;
		this.releaseDate = releaseDate;
		this.movieArtists = movieArtists;
		this.poster = poster;
	}

	public long getmovieId() {
		return movieId;
	}

	public void setmovieId(long movieId) {
		this.movieId = movieId;
	}

	public String getmovieName() {
		return movieName;
	}

	public void setmovieName(String movieName) {
		this.movieName = movieName;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public String getreleaseDate() {
		return releaseDate;
	}

	public void setreleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public Set<String> getmovieArtists() {
		return movieArtists;
	}

	public void setmovieArtists(Set<String> movieArtists) {
		this.movieArtists = movieArtists;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}
	
	
	
	

}
