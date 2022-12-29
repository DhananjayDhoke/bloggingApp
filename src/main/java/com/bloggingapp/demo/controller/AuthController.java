package com.bloggingapp.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bloggingapp.demo.entites.User;
import com.bloggingapp.demo.payloads.JwtAuthRequest;
import com.bloggingapp.demo.payloads.JwtAuthResponce;
import com.bloggingapp.demo.security.JwtTokenHelper;
import com.bloggingapp.demo.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
	
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private UserService userService;
    
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponce> createToken (@RequestBody JwtAuthRequest jwtAuthRequest){
		
		this.authenticate(jwtAuthRequest.getUsername(),jwtAuthRequest.getPassword());
		
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtAuthRequest.getUsername());
		
		
		String token = this.jwtTokenHelper.generateToken(userDetails);
		
		System.out.println(token);
		
		JwtAuthResponce  responce = new JwtAuthResponce();
		
		responce.setToken(token);
		
		return new ResponseEntity<>(responce,HttpStatus.OK);
	}
	
	@PostMapping("/register")
	public ResponseEntity<User> registerUser(@Valid @RequestBody User user){
		
		 User registerUser = userService.registerUser(user);
	
		return new ResponseEntity<>(registerUser,HttpStatus.OK);
	}
	

	private void authenticate(String username, String password) {
	
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
		authenticationManager.authenticate(authenticationToken);
		
		
	}
	
}
