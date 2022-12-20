package com.bloggingapp.demo.service;

import java.util.List;

import com.bloggingapp.demo.entites.Post;

public interface PostService {
    
	Post createPost (Post post,Integer userId,Integer categoryId);
	
	Post updatePost (Post post,Integer postId);
	
	void deletePost (Integer postId);
	
	List<Post> getAllPost (Integer pageNumber,Integer pageSize);
	
	Post getPostById (Integer postId);
	
	// get all post by user 
	List<Post> getPostByUserId (Integer userId);
	
	// get all post category
	List<Post> getPostByCategoryId (Integer categoryId);
}
