package com.bloggingapp.demo.repositery;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bloggingapp.demo.entites.Category;
import com.bloggingapp.demo.entites.Post;
import com.bloggingapp.demo.entites.User;

public interface PostRepo extends JpaRepository<Post, Integer>{
      
	List<Post> findByCategory(Category category);
    List<Post> findByUser(User user);
    
    @Query("select p from Post p where p.title like :key")
    List<Post> searchByTitle(@Param("key")String keyword);
}
