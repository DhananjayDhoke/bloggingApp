package com.bloggingapp.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bloggingapp.demo.entites.User;
import com.bloggingapp.demo.exception.ResourceNotFoundException;
import com.bloggingapp.demo.repositery.UserRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    
	@Autowired 
	private UserRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepo.findByEmail(username).orElseThrow(()->new ResourceNotFoundException("user not foud with email"));
		
		return user;
	}

}
