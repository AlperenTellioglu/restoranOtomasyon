package com.restoranOtomasyon.business.abstracts;

import java.util.List;

import com.restoranOtomasyon.business.responses.GetAllCategoriesResponse;

public interface CategoryService {
	
	public List<GetAllCategoriesResponse> getAllCategories();
}
