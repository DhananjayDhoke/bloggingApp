package com.bloggingapp.demo.service;

import java.util.List;

import com.bloggingapp.demo.entites.Category;

public interface CategoryService {
  
	Category createCategory (Category category);
	
	Category getCategory (Integer Id);
	
	Category updateCategory(Category category,Integer Id);
	
	void deleteCategory (Integer Id);
	
	List<Category> getAllCategeory(); 
}
