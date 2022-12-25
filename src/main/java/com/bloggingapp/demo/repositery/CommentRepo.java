package com.bloggingapp.demo.repositery;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bloggingapp.demo.entites.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer>{

}
