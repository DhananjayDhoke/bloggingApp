package com.bloggingapp.demo.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bloggingapp.demo.entites.Comment;
import com.bloggingapp.demo.entites.Post;
import com.bloggingapp.demo.exception.ResourceNotFoundException;
import com.bloggingapp.demo.repositery.CommentRepo;
import com.bloggingapp.demo.repositery.PostRepo;
import com.bloggingapp.demo.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService{
    
	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private PostRepo postRepo;
	
	
	@Override
	public Comment createComment(Comment comment, Integer postId) {
		
		Post post = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post not found with id "+postId));
		comment.setPost(post);
		return commentRepo.save(comment);
	}

	@Override
	public void deleteComment(Integer commentId) {
		
		Comment deltedComment = commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("comment not found with id "+commentId));
		
		commentRepo.delete(deltedComment);
	}

}
