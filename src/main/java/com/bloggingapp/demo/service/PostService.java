package com.bloggingapp.demo.service;

import java.util.List;

import com.bloggingapp.demo.entites.Post;

public interface PostService {
    
	Post createPost (Post post,Integer userId,Integer categoryId);
	
	Post updatePost (Post post,Integer postId);
	
	void deletePost (Integer postId);
	
	List<Post> getAllPost ();
	
	List<Post> getPostById (Integer postId);
	
	List<Post> getPostByUserId (Integer userId);
	
	List<Post> getPostByCategoryId (Integer categoryId);
}
