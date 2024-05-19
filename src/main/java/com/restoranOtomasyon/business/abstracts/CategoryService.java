package com.restoranOtomasyon.business.abstracts;

import java.util.List;

import com.restoranOtomasyon.business.responses.GetAllCategoriesResponse;
import com.restoranOtomasyon.business.responses.GetAllCategoriesWithStatusResponse;

public interface CategoryService {
	
	public List<GetAllCategoriesResponse> getAllCategories();
	public List<GetAllCategoriesWithStatusResponse> getAllCategoriesWithStatus();
}
