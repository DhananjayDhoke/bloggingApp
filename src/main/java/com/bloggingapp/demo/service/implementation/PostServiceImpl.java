package com.bloggingapp.demo.service.implementation;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bloggingapp.demo.entites.Category;
import com.bloggingapp.demo.entites.Post;
import com.bloggingapp.demo.entites.User;
import com.bloggingapp.demo.exception.ResourceNotFoundException;
import com.bloggingapp.demo.repositery.CategoryRepo;
import com.bloggingapp.demo.repositery.PostRepo;
import com.bloggingapp.demo.repositery.UserRepo;
import com.bloggingapp.demo.service.PostService;

@Service
public class PostServiceImpl implements PostService{
    
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Override
	public Post createPost(Post post,Integer userId,Integer categoryId) {
		
		User user = userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user not found with id "+userId));
		Category category  = categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("user not found with id "+categoryId));
		
		post.setUser(user);
		post.setAddedDate(new Date());
		post.setCategory(category);
		post.setImageName("Default.png");
		return postRepo.save(post);
	}

	@Override
	public Post updatePost(Post post, Integer postId) {
		 postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("post not found with id "+postId));
		return postRepo.save(post);
	}

	@Override
	public void deletePost(Integer postId) {
	    
		Post deltedPost = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post not found with id "+postId));
		postRepo.delete(deltedPost);
		
	}

	@Override
	public List<Post> getAllPost() {
	 return postRepo.findAll();
	}

	@Override
	public List<Post> getPostById(Integer postId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Post> getPostByUserId(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Post> getPostByCategoryId(Integer categoryId) {
		// TODO Auto-generated method stub
		return null;
	}

}
