package com.bloggingapp.demo.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bloggingapp.demo.entites.User;
import com.bloggingapp.demo.exception.UserNotFoundException;
import com.bloggingapp.demo.repositery.UserRepo;
import com.bloggingapp.demo.service.UserService;
@Service
public class UserServiceImpl implements UserService{
    
	@Autowired
	private UserRepo userRepo;
	
	@Override
	public User createUser(User user) {
	  return userRepo.save(user);
	}

	@Override
	public User updateUser(User user, Integer userId) {
		
		Optional<User> updatedUser =userRepo.findById(userId);
		
		if(updatedUser.isPresent()) {
			return userRepo.save(user);
		}else {
		    throw new UserNotFoundException("user not found with id "+ userId);
		}
		
	}

	@Override
	public User getUserById(Integer userId) {
	    
		return userRepo.findById(userId)
				.orElseThrow(()-> new UserNotFoundException("user not found with id " + userId));
	}

	@Override
	public User deleteUserById(Integer userId) {
		
		User user = userRepo.findById(userId).orElseThrow(()-> new UserNotFoundException("User not found with id "+ userId));
		
		userRepo.delete(user);
		
		return user;
	
		
	}

	@Override
	public List<User> getallUser() {
		
		return userRepo.findAll();
	}

}
