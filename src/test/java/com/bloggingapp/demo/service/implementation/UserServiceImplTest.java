package com.bloggingapp.demo.service.implementation;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bloggingapp.demo.entites.User;
import com.bloggingapp.demo.repositery.UserRepo;
import com.bloggingapp.demo.service.UserService;

@ExtendWith(MockitoExtension.class)
@SpringBootApplication
class UserServiceImplTest {
    
	
	private UserServiceImpl userServiceImpl;
	
	@Mock
	private UserRepo userRepo;
	
	@BeforeEach
	void setUp() {
		this.userServiceImpl = new UserServiceImpl(this.userRepo);
		 User user = new User();
		 user.setName("abc");
		
		 Optional<User> user1 = Optional.of(user);
		 
		Mockito.when(userRepo.findById(1)).thenReturn(user1);
	}
	
	@Test
	void  getallUser() {
		userServiceImpl.getallUser();
		verify(userRepo).findAll();
	}
	
	@Test
	public void getUserById() {
		String username = "abc";
		User userById = userServiceImpl.getUserById(1);
		assertEquals(username, userById.getName());
	}
	
	
	

}
