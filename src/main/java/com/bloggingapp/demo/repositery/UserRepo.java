package com.bloggingapp.demo.repositery;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bloggingapp.demo.entites.User;

public interface UserRepo extends JpaRepository<User, Integer>{
   
	Optional<User> findByEmail(String email); 
}
