package com.bloggingapp.demo.repositery;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bloggingapp.demo.entites.Post;
import com.bloggingapp.demo.entites.User;

@SpringBootTest
class PostRepoTest {
    
	@Autowired
	private PostRepo postRepo;
	
	@Test
	void findByUser() {
	 // User user = new User(1,"dhananjay","dhananjay@gmail.com","dhan@123","very good",new ArrayList<>());
	  User user = new User();
	  user.setId(102);
	  List<Post> findByUser = postRepo.findByUser(user);
	  assertThat(findByUser).isNotEmpty();
	  
	}

}
