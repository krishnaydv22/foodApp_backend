package com.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.main.model.Category;
import com.main.model.User;
import com.main.service.CategoryService;
import com.main.service.UserService;

@RestController
@RequestMapping("/api")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/admin/category")
	public ResponseEntity<Category> createCategory(@RequestBody Category category,
			                                        @RequestHeader("Authorization") String jwt) throws Exception{
		User user = userService.findUserByJwtToken(jwt);
		Category createdCatogory = categoryService.createCatogory(category.getName(),user.getId());
		
		return new ResponseEntity<Category>(createdCatogory,HttpStatus.CREATED);
	}
	
	@GetMapping("/category/restaurant/{id}")
	public ResponseEntity<List<Category>> getRestaurantCategory(@PathVariable Long id,
			                                        @RequestHeader("Authorization") String jwt) throws Exception{
		User user = userService.findUserByJwtToken(jwt);
		List<Category> categories = categoryService.findCategoryByRestaurantId(id);
		
		return new ResponseEntity<>(categories,HttpStatus.CREATED);
	}
	
	@GetMapping("/category/admin/restaurant/{id}")
	public ResponseEntity<Category> getCategory(@PathVariable Long id,
			                                        @RequestHeader("Authorization") String jwt) throws Exception{
		User user = userService.findUserByJwtToken(jwt);
		Category category = categoryService.findCategoryById(id);
		
		return new ResponseEntity<>(category,HttpStatus.CREATED);
	}
	
	

}
