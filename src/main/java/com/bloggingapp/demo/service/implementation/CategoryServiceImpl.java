package com.bloggingapp.demo.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bloggingapp.demo.entites.Category;
import com.bloggingapp.demo.exception.ResourceNotFoundException;
import com.bloggingapp.demo.repositery.CategoryRepo;
import com.bloggingapp.demo.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepo categeoryRepo;

	@Override
	public Category createCategory(Category category) {
		return categeoryRepo.save(category);
	}

	@Override
	public Category getCategory(Integer categoryId) {
		return categeoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category not found with id " +categoryId));
	}

	@Override
	public Category updateCategory(Category category, Integer categoryId) {

		Category cat = categeoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category not found with id " +categoryId));

		cat.setCategoryTitle(category.getCategoryTitle());
		cat.setCategoryDescription(category.getCategoryDescription());
		return categeoryRepo.save(cat);
	}

	@Override
	public void deleteCategory(Integer categoryId) {

		Category cat = categeoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category not found with id "+categoryId ));
		categeoryRepo.delete(cat);

	}

	@Override
	public List<Category> getAllCategeory() {
		return categeoryRepo.findAll();
	}

}
