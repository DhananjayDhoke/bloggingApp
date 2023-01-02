package com.bloggingapp.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bloggingapp.demo.entites.Category;
import com.bloggingapp.demo.service.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@PostMapping("/create")
	public ResponseEntity<Category> createCategeoryHandler (@Valid @RequestBody Category category){
         
	    
		Category createdCategory = categoryService.createCategory(category);

		return new ResponseEntity<>(createdCategory,HttpStatus.OK);

	}

	@PutMapping("/update/{Id}")
    public ResponseEntity<Category> updateCategoryHandler(@Valid @RequestBody Category category,@PathVariable Integer categoryId){

		Category updatedCategory = categoryService.updateCategory(category, categoryId);

		return new ResponseEntity<>(updatedCategory,HttpStatus.OK);
	}
    
	@GetMapping("/get/{Id}")
	public ResponseEntity<Category> getCategeoryHandler (@PathVariable Integer categoryId){

		Category getCategory = categoryService.getCategory(categoryId);

		return new ResponseEntity<>(getCategory,HttpStatus.OK);

	}
	
	@GetMapping("/getall")
	public ResponseEntity<List<Category>> getAllCategeoryHandler (){

		List<Category> getAllCategory = categoryService.getAllCategeory();

		return new ResponseEntity<>(getAllCategory,HttpStatus.OK);

	}
    
	@DeleteMapping("/delete/{Id}")
	public void getdeleteCategeoryHandler (@PathVariable Integer categoryId){

		 categoryService.deleteCategory(categoryId);

	}
	

}
