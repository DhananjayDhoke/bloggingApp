package com.bloggingapp.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bloggingapp.demo.entites.User;


public interface UserService {
    
	User registerUser(User user);
	
	User createUser(User user);
	
	User updateUser(User user);
	
	User getUserById (Integer userId);
	
	User deleteUserById (Integer userId);
	
	List<User> getallUser();
	
	
}
