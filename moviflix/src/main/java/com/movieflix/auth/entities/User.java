package com.movieflix.auth.entities;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User implements UserDetails {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long userId;
	
	@Column(unique = true)
	@NotBlank(message = "Please enter user name")
	private String userName;
	
	@Column(unique = true)
	@NotBlank(message = "Please enter email")
	@Email(message = "Please enter email in proper format")
	private String email;
	
	@Column(unique = true)
	@Size(min = 5,message = "Please enter atleast 5 characters")
	@NotBlank(message = "Please enter password")
	private String password;
	
	@OneToOne(mappedBy = "refreshToken")
	private RefreshToken refreshToken;

	@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}

	public User(long userId, @NotBlank(message = "Please enter user name") String userName,
			@NotBlank(message = "Please enter email") @Email(message = "Please enter email in proper format") String email,
			@Size(min = 5, message = "Please enter atleast 5 characters") @NotBlank(message = "Please enter password") String password,
			RefreshToken refreshToken) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.refreshToken = refreshToken;
	}

	public User() {
		super();
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public RefreshToken getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(RefreshToken refreshToken) {
		this.refreshToken = refreshToken;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	

}
