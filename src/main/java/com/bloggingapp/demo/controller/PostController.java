package com.bloggingapp.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bloggingapp.demo.entites.Post;
import com.bloggingapp.demo.payloads.PostResponce;
import com.bloggingapp.demo.service.PostService;

@RestController
@RequestMapping("/api/post")

public class PostController {

	@Autowired
	private PostService postService;

	// create post
	@PostMapping("user/{userId}/category/{categoryId}")
	public ResponseEntity<Post> postdata (@RequestBody Post post,@PathVariable Integer userId,@PathVariable Integer categoryId){

		Post postByUser = postService.createPost(post, userId, categoryId);

		return new ResponseEntity<>(postByUser,HttpStatus.CREATED);

	}

	// get list of post by user
	@GetMapping("/user/{userId}")

	public ResponseEntity<List<Post>> getallPostByUser (@PathVariable Integer userId){

		List <Post> postByUser = postService.getPostByUserId(userId);

		return new ResponseEntity<>(postByUser,HttpStatus.OK);
	}

	// get list of post by category
	@GetMapping("/category/{categoryId}")

	public ResponseEntity<List<Post>> getallPostByCategory (@PathVariable Integer categoryId){

		List <Post> postByCategory = postService.getPostByCategoryId(categoryId);

		return new ResponseEntity<>(postByCategory,HttpStatus.OK);
	}

	// get single post by id
	@GetMapping("/{postId}")

	public ResponseEntity<Post> getSinglePostById (@PathVariable Integer postId){

		Post getPostById = postService.getPostById(postId);

		return new ResponseEntity<>(getPostById,HttpStatus.OK);
	}

	// get all post
	@GetMapping("/getallpost")

	public ResponseEntity<PostResponce> getAllPostById (
			@RequestParam(value = "pageNumber",defaultValue = "0",required = false) Integer pageNumber,
			@RequestParam(value = "pageSize",defaultValue ="10",required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = "postId", required = false) String sortBy
			){

		 PostResponce allPost = postService.getAllPost(pageNumber, pageSize, sortBy);

		return new ResponseEntity<>(allPost,HttpStatus.OK);
	}
	
	// get all post
		@DeleteMapping("/delete/{postId}")

		public void deleteById (@PathVariable Integer postId){

		    postService.deletePost(postId);
		}
		
		// update post by id
		@PutMapping("/update/{postId}")

		public ResponseEntity<Post> updatPost(@RequestBody Post post, @PathVariable Integer postId){

			Post updatedPost = postService.updatePost(post, postId);

			return new ResponseEntity<>(updatedPost,HttpStatus.OK);
		}	
		
	
}
