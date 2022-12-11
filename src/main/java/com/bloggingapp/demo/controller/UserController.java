package com.bloggingapp.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bloggingapp.demo.entites.User;
import com.bloggingapp.demo.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {
    
	@Autowired
	private UserService userService;
	
	
	@GetMapping("/getuser")
	public ResponseEntity<List<User>> getAllUserHandler() {
		
		List<User> allUser = userService.getallUser();
		
		return new ResponseEntity<List<User>>(allUser,HttpStatus.OK);
	}
	
	@GetMapping("/getuser/{userId}")
	public ResponseEntity<User> getUserByIdHandler(@PathVariable Integer userId) {
		
		User getUser = userService.getUserById(userId);
		
		return new ResponseEntity<>(getUser,HttpStatus.OK);
	}
	
	@PostMapping("/creatuser")
	public ResponseEntity<User> createUserHandler(@RequestBody User user){
		
		User createdUser = userService.createUser(user);
		
		return new ResponseEntity<>(createdUser,HttpStatus.OK);
	}
	
	@PutMapping("/updateuser/{userId}")
	public ResponseEntity<User> updateUserByIdHandler(@RequestBody User user,@PathVariable Integer userId) {
		
		User updateUser = userService.updateUser(user, userId);
		
		return new ResponseEntity<>(updateUser,HttpStatus.OK);
	}
	
	
	@DeleteMapping("/deleteuser/{userId}")
	public ResponseEntity<User> deleteUserByIdHandler(@PathVariable Integer userId) {
		
		User deleteUser = userService.deleteUserById(userId);
		
		return new ResponseEntity<>(deleteUser,HttpStatus.OK);
	}
	
	
}
