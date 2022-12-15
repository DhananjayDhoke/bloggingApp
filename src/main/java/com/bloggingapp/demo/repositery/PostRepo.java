package com.bloggingapp.demo.repositery;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bloggingapp.demo.entites.Post;

public interface PostRepo extends JpaRepository<Post, Integer>{
      
	
}
