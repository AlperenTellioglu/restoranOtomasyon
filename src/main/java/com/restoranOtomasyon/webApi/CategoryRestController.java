package com.restoranOtomasyon.webApi;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restoranOtomasyon.business.abstracts.CategoryService;
import com.restoranOtomasyon.business.responses.GetAllCategoriesResponse;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class CategoryRestController {

	private CategoryService categoryService;
	
	@GetMapping("/categories")
	public List<GetAllCategoriesResponse> getAllCategories(){
		
		return categoryService.getAllCategories();
	}
	
}
