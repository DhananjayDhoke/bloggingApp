package com.bloggingapp.demo.service.implementation;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.bloggingapp.demo.entites.Category;
import com.bloggingapp.demo.entites.Post;
import com.bloggingapp.demo.entites.User;
import com.bloggingapp.demo.exception.ResourceNotFoundException;
import com.bloggingapp.demo.payloads.PostResponce;
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
	public Post updatePost( Post post, Integer postId) {
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
	public PostResponce getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir) {
	 
		Sort sort = null;
		if(sortDir.equalsIgnoreCase("asc")) {
			sort = Sort.by(sortBy).ascending();
		}
		else {
			sort = Sort.by(sortBy).descending();
		}
		
		Pageable p = PageRequest.of(pageNumber, pageSize,sort);
	    
		Page<Post>  pagePost = postRepo.findAll(p);
		List<Post> content=pagePost.getContent();
		
	 PostResponce postResponce = new PostResponce();
		 
//		 PostResponce postResponce =PostResponce
//				 .builder()
//				 .content(content)
//				 .lastPage(pagePost.isLast()).build();
		 
		 postResponce.setContent(content);
		 postResponce.setPageNumber(pagePost.getNumber());
		 postResponce.setPageSize(pagePost.getSize());
		 postResponce.setTotalElements(pagePost.getTotalElements());
		 postResponce.setTotalPages(pagePost.getTotalPages());
		 postResponce.setLastPage(pagePost.isLast());
		
		return postResponce;
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

	@Override
	public List<Post> searchPost(String keyword) {
		
		return postRepo.searchByTitle("%"+keyword+"%");
	}

}
