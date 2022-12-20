package com.bloggingapp.demo.service.implementation;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
		Post updatepost = postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("post not found with id "+postId));
		  updatepost.setTitle(post.getTitle());
		  updatepost.setContent(post.getContent());
		  
		  return postRepo.save(updatepost);
	}

	@Override
	public void deletePost(Integer postId) {
	    
		Post deltedPost = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post not found with id "+postId));
		postRepo.delete(deltedPost);
		
	}

	@Override
	public List<Post> getAllPost(Integer pageNumber,Integer pageSize) {
	 
		Pageable p = PageRequest.of(pageNumber, pageSize);
	    
		Page<Post>  pagePost = postRepo.findAll(p);
		List<Post> content=pagePost.getContent();
		
		return content;
	}

	@Override
	public Post getPostById(Integer postId) {
		return postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post not found with id "+postId));
	}

	@Override
	public List<Post> getPostByUserId(Integer userId) {
		
		User user = userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("post not found with userid "+ userId));
		return postRepo.findByUser(user);
	}

	@Override
	public List<Post> getPostByCategoryId(Integer categoryId) {
		
		Category cat = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("post not found with cat id "+categoryId));
		
		return postRepo.findByCategory(cat);
	}

}
