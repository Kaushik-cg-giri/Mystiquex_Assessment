package com.movieflix.dto;

import java.time.LocalDate;
import java.util.Set;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class MovieDTO {
	
		private long movieId;
		
		@NotBlank(message = "please provide movie name..!")
		private String movie_name;
		
		@NotNull
		private float rating;
		
		@NotBlank(message = "please provide movie release date..!")
		private String releaseDate;
		

		private Set<String> movieArtists;
		
		@NotBlank(message = "please provide movie poster..!")
		private String poster;
		
		@NotBlank(message = "please provide movie poster url..!")
		private String posterURL;

		public MovieDTO() {
			super();
		}

		public MovieDTO( long movieId,@NotBlank(message = "please provide movie name..!") String movie_name,
				@NotNull float rating,
				@NotBlank(message = "please provide movie release date..!") String releaseDate,
				Set<String> movieArtists, @NotBlank(message = "please provide movie poster..!") String poster,@NotBlank(message = "please provide movie poster url..!") String posterURL) {
			super();
			this.movieId = movieId;
			this.movie_name = movie_name;
			this.rating = rating;
			this.releaseDate = releaseDate;
			this.movieArtists = movieArtists;
			this.poster = poster;
			this.posterURL = posterURL;
		}

		public long getmovieId() {
			return movieId;
		}

		public void setmovieId(long movieId) {
			this.movieId = movieId;
		}

		public String getMovie_name() {
			return movie_name;
		}

		public void setMovie_name(String movie_name) {
			this.movie_name = movie_name;
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

		public String getPosterURL() {
			return posterURL;
		}

		public void setPosterURL(String posterURL) {
			this.posterURL = posterURL;
		}
		
		

}
