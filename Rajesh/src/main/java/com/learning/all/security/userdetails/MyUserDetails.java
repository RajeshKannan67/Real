package com.learning.all.security.userdetails;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.learning.all.repositries.UserRepo;
@Service
public class MyUserDetails implements UserDetailsService{

	@Autowired 
	private UserRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<com.learning.all.entity.UserDetails> user = userRepo.findByname(username);
		  System.out.println("username ---> "+username);
		if(user.isPresent()) {
			
			var Obj = user.get();
			
			return User.builder()
					   .username(Obj.getName())
					   .roles(getRoles(Obj))
					   .password(Obj.getPassword())
					   .build();
					   
		}else {
			throw new UsernameNotFoundException(username);
		}
		
	}

private String getRoles(com.learning.all.entity.UserDetails user) {
		
		if(user.getRole()==null) {
			return  "USER";
		}
		System.out.println(user.getRole());
		return user.getRole();
	}
}
