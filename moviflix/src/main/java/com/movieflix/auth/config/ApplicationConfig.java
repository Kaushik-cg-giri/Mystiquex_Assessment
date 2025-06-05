package com.movieflix.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.movieflix.auth.repositories.UserRepository;

@Configuration
public class ApplicationConfig {
	
	@Autowired
	private UserRepository userRepository;
	

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByEmail(username)
        								 .orElseThrow(()-> new UsernameNotFoundException("User not found with the email"+username));
                
    }
    
    @Bean
    public AuthenticationProvider authenticationProvider() {
    	
    	DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
    	authenticationProvider.setUserDetailsService(userDetailsService());
    	authenticationProvider.setPasswordEncoder(PasswordEncoder());
		return null;
    	
    }

    @Bean
	private PasswordEncoder PasswordEncoder() {
		
    	return new BCryptPasswordEncoder();

	}

}
