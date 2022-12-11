package com.bloggingapp.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bloggingapp.demo.entites.User;

@Service
public interface UserService {
   
	User createUser(User user);
	
	User updateUser(User user , Integer userId);
	
	User getUserById (Integer userId);
	
	User deleteUserById (Integer userId);
	
	List<User> getallUser();
	
	
}
