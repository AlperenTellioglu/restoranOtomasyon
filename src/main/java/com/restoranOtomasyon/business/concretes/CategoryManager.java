package com.restoranOtomasyon.business.concretes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.restoranOtomasyon.business.abstracts.CategoryService;
import com.restoranOtomasyon.business.responses.GetAllCategoriesResponse;
import com.restoranOtomasyon.business.responses.GetAllMenusResponse;
import com.restoranOtomasyon.dataAccess.abstracts.CategoryRepository;
import com.restoranOtomasyon.entities.concretes.Category;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CategoryManager implements CategoryService{

	private CategoryRepository categoryRepository;

	@Override
	public List<GetAllCategoriesResponse> getAllCategories() {
		List<Category> categories = categoryRepository.findAll();
		
		List<GetAllCategoriesResponse> categoryResponseList = new ArrayList<GetAllCategoriesResponse>();
		
		for (Category category : categories) {
			GetAllCategoriesResponse responseItem = new GetAllCategoriesResponse();
			responseItem.setId(category.getCategoryId());
			responseItem.setName(category.getCategoryName());
			
			List<GetAllMenusResponse> menuResponseList = category.getMenus().stream()
				    .filter(menu -> !"gosterme".equals(menu.getMenuStatus()))
				    .map(menu -> {
				        GetAllMenusResponse menuResponse = new GetAllMenusResponse();
				        menuResponse.setId(menu.getMenuId());
				        menuResponse.setName(menu.getMenuName());
				        menuResponse.setPrice(menu.getPrice());
				        menuResponse.setExpense(menu.getExpense());
				        menuResponse.setStatus(menu.getMenuStatus());
				        return menuResponse;
				    }).collect(Collectors.toList());
			
				responseItem.setMenus(menuResponseList);
				categoryResponseList.add(responseItem);
			}
		
		
		return categoryResponseList;
	}
	
	
}
