package com.bloggingapp.demo.repositery;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bloggingapp.demo.entites.User;

public interface UserRepo extends JpaRepository<User, Integer>{

}
