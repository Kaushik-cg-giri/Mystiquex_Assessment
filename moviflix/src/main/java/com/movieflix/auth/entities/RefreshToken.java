package com.movieflix.auth.entities;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "refreshtokens")
public class RefreshToken {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long tokenId;
	
	@Column(nullable = false,length = 500)
	@NotBlank(message = "Please enter refresh token")
	private String refreshToken;
	
	@Column(nullable = false)
	private Instant expirationTime;
	
	@OneToOne
	private User user;
	
	

	public RefreshToken() {
		super();
	}



	public RefreshToken(long tokenId, @NotBlank(message = "Please enter refresh token") String refreshToken,
			Instant expirationTime, User user) {
		super();
		this.tokenId = tokenId;
		this.refreshToken = refreshToken;
		this.expirationTime = expirationTime;
		this.user = user;
	}



	public long getTokenId() {
		return tokenId;
	}



	public void setTokenId(long tokenId) {
		this.tokenId = tokenId;
	}



	public String getRefreshToken() {
		return refreshToken;
	}



	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}



	public Instant getExpirationTime() {
		return expirationTime;
	}



	public void setExpirationTime(Instant expirationTime) {
		this.expirationTime = expirationTime;
	}



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}
	
	
	

}
