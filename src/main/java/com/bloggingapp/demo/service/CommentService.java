package com.bloggingapp.demo.service;

import com.bloggingapp.demo.entites.Comment;

public interface CommentService {
    
	Comment createComment(Comment comment,Integer postId);
	
	void deleteComment(Integer commentId);
	
}
