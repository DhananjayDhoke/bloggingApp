package com.bloggingapp.demo.entites;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Category {
    
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String categoryId;
	private String categoryTitle;
	private String categoryDescription;
	
}
