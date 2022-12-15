package com.bloggingapp.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bloggingapp.demo.entites.Post;
import com.bloggingapp.demo.service.PostService;

@RestController
@RequestMapping("/api/post")

public class PostController {
	
	@Autowired
	private PostService postService;
    
	@PostMapping("/user/{userId}/category/{categoryId}")
	public ResponseEntity<Post> postdata (@RequestBody Post post,@PathVariable Integer userId,@PathVariable Integer categoryId){
		
		Post postByUser = postService.createPost(post, userId, categoryId);
		
		return new ResponseEntity<>(postByUser,HttpStatus.CREATED);
		
	}
}
