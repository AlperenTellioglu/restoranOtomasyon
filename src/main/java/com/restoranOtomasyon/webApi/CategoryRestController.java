package com.restoranOtomasyon.webApi;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restoranOtomasyon.business.abstracts.CategoryService;
import com.restoranOtomasyon.business.requests.CreateCategoryRequest;
import com.restoranOtomasyon.business.requests.UpdateCategoryRequest;
import com.restoranOtomasyon.business.responses.GetAllCategoriesResponse;
import com.restoranOtomasyon.dataAccess.abstracts.CategoryRepository;
import com.restoranOtomasyon.entities.concretes.Category;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class CategoryRestController {

	private CategoryService categoryService;
	private CategoryRepository categoryRepository;
	
	@GetMapping("/categories")
	public List<GetAllCategoriesResponse> getAllCategories(){
		
		return categoryService.getAllCategories();
	}
	
	@PostMapping("/createCategory")
	public void addCategory(@RequestBody CreateCategoryRequest categoryRequest) {
		Category category = new Category();
		
		category.setCategoryName(categoryRequest.getName());
		
		this.categoryRepository.save(category);
	}
	
	@PutMapping("/updateCategory")
	public void updateCategory(UpdateCategoryRequest categoryRequest) {
		Category category = categoryRepository.findById(categoryRequest.getId())
				.orElseThrow();
		
		category.setCategoryName(categoryRequest.getName());
		
		this.categoryRepository.save(category);
		
	}
	
	@DeleteMapping("/deleteCategory")
	public void deleteCategory(int categoryId) {
		Category category = categoryRepository.findById(categoryId)
				.orElseThrow();
					
		this.categoryRepository.delete(category);
	}
	
}
