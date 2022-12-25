package com.bloggingapp.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bloggingapp.demo.entites.Comment;
import com.bloggingapp.demo.service.CommentService;

@RestController
@RequestMapping("api/comment/")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
   
	@PostMapping("/post/{postId}")
	public ResponseEntity<Comment> createComment(@RequestBody Comment comment,@PathVariable Integer postId){
	   
		Comment createdComment = commentService.createComment(comment, postId);
		
		return new ResponseEntity<Comment>(createdComment,HttpStatus.OK);
	}
	
	@DeleteMapping("/{commentId}")
	public void  deleteComment(@PathVariable Integer commentId){
	   
		 commentService.deleteComment(commentId);
		
	}
	
	
}
