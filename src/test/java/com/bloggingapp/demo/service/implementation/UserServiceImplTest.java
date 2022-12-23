package com.bloggingapp.demo.service.implementation;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.bloggingapp.demo.entites.User;
import com.bloggingapp.demo.repositery.UserRepo;
import com.bloggingapp.demo.service.UserService;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    
	
	private UserServiceImpl userServiceImpl;
	
	@Mock
	private UserRepo userRepo;
	
	@Test
	void  getallUser() {
		userServiceImpl.getallUser();
		verify(userRepo).findAll();
	}
	
	@BeforeEach
	void setUp() {
		this.userServiceImpl = new UserServiceImpl(this.userRepo);
	}
	

}
