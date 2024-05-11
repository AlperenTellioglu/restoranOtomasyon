package com.restoranOtomasyon.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restoranOtomasyon.business.abstracts.CategoryService;
import com.restoranOtomasyon.business.responses.GetAllCategoriesResponse;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/qrMenus")
@AllArgsConstructor
public class QRMenuController {

	CategoryService categoryService;
	
	@GetMapping
    public List<GetAllCategoriesResponse> getAllMenus() {
        return categoryService.getAllCategories();
    }
	
}
