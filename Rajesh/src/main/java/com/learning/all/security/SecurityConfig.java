package com.learning.all.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.learning.all.security.userdetails.MyUserDetails;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private MyUserDetails user;
	
	
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider () {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(user);
		provider.setPasswordEncoder(encoder());
		return provider;
		
	}
	
	@Bean
	public SecurityFilterChain chain(HttpSecurity http) throws Exception{
		
		return	http
						.csrf(srf ->srf.disable())
						.authorizeHttpRequests(req -> {
					//	req.requestMatchers("//**").permitAll();
						req.requestMatchers("/principal/**").hasRole("PRINCIPAL");
						req.requestMatchers("/teacher/**").hasRole("TEACHER");
						req.requestMatchers("/student/**").hasRole("STUDENT");
						req.anyRequest().permitAll();
						})
						
			.formLogin(login ->{
				login.loginPage("/login")
				.successHandler(new AuthendicateSucessHandler())
				.permitAll();
				
				
			})
			
			.build();
		}
	
	@Bean
	public UserDetailsService detailsService() {
		
		return user;
	}
	
}
