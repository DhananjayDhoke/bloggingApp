package com.bloggingapp.demo.service;

import java.util.List;
import com.bloggingapp.demo.entites.Category;

public interface CategoryService {
  
	Category createCategory (Category category);
	
	Category getCategory (Integer categoryId);
	
	Category updateCategory(Category category,Integer categoryId);
	
	void deleteCategory (Integer categoryId);
	
	List<Category> getAllCategeory(); 
}
