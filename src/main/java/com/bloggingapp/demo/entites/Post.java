package com.bloggingapp.demo.entites;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@JsonIgnoreProperties({"hibernateLazyInitializer","handler","comments"})
public class Post {
    
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer postId;
	
	@Column(nullable = false, length = 100)
	private String title;
	
	private String content;
	
	private String imageName;
	
	private Date addedDate;
	
	@ManyToOne
	private User user;
	
	@ManyToOne
	private Category category;
	
	@OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
	private Set<Comment> comments = new HashSet<>();
	
}
