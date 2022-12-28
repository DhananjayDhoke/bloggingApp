package com.bloggingapp.demo.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bloggingapp.demo.entites.User;
import com.bloggingapp.demo.exception.ResourceNotFoundException;
import com.bloggingapp.demo.repositery.UserRepo;
import com.bloggingapp.demo.service.UserService;
@Service
public class UserServiceImpl implements UserService{
    
	@Autowired
	private UserRepo userRepo;
	
//	@Autowired
//	private BCryptPasswordEncoder bEncoder;
	
	public UserServiceImpl(UserRepo userRepo) {
		super();
		this.userRepo = userRepo;
	}
	
	@Override
	public User createUser(User user) {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	  user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));	
	  return userRepo.save(user);
	}

	@Override
	public User updateUser(User user) {
		
		Optional<User> updatedUser =userRepo.findById(user.getId());
		
		if(updatedUser.isPresent()) {
			return userRepo.save(user);
		}else {
		    throw new ResourceNotFoundException("user not found with id "+ user.getId());
		}
		
	}

	@Override
	public User getUserById(Integer userId) {
	    
		return userRepo.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("user not found with id " + userId));
	}

	@Override
	public User deleteUserById(Integer userId) {
		
		User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User not found with id "+ userId));
		
		userRepo.delete(user);
		
		return user;
	
		
	}

	@Override
	public List<User> getallUser() {
		
		return userRepo.findAll();
	}



}
