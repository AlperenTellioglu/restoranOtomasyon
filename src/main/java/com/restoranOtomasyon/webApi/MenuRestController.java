package com.restoranOtomasyon.webApi;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restoranOtomasyon.business.requests.CreateMenuRequest;
import com.restoranOtomasyon.business.requests.UpdateMenuRequest;
import com.restoranOtomasyon.dataAccess.abstracts.CategoryRepository;
import com.restoranOtomasyon.dataAccess.abstracts.MenuRepository;
import com.restoranOtomasyon.entities.concretes.Category;
import com.restoranOtomasyon.entities.concretes.Menu;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class MenuRestController {

	private MenuRepository menuRepository;
	private CategoryRepository categoryRepository;
	
	@PostMapping("/createMenu")
	public void addMenu(CreateMenuRequest menuRequest) {
		Menu menu = new Menu();
		
		Category category = categoryRepository.findById(menuRequest.getCategoryId())
				.orElseThrow();
		
		menu.setMenuName(menuRequest.getName());
		menu.setExpense(menuRequest.getExpense());
		menu.setPrice(menuRequest.getPrice());
		menu.setCategory(category);
		menu.setMenuStatus("goster");
		
		this.menuRepository.save(menu);
	}
	
	@PostMapping("/changeMenuStatus")
	public void dontShowOrder(int menuId) {
		Menu menu = menuRepository.findById(menuId)
				.orElseThrow();

		if(menu.getMenuStatus().equals("goster")) {
			menu.setMenuStatus("gosterme");
		}
		
		else if(menu.getMenuStatus().equals("gosterme")) {
			menu.setMenuStatus("goster");
		}
		
		this.menuRepository.save(menu);

	}
	
	
	@PutMapping("/updateMenu")
	public void updateCategory(UpdateMenuRequest menuRequest) {
		Menu menu = menuRepository.findById(menuRequest.getId())
				.orElseThrow();
		
		Category category = categoryRepository.findById(menuRequest.getCategoryId())
				.orElseThrow();
		
		menu.setMenuName(menuRequest.getName());
		menu.setPrice(menuRequest.getPrice());
		menu.setExpense(menuRequest.getExpense());
		menu.setCategory(category);
		
		this.menuRepository.save(menu);
		
	}
	
	@DeleteMapping("/deleteMenu")
	public void deleteMenu(int menuId) {
	    Menu menu = menuRepository.findById(menuId)
	            .orElseThrow();


	    this.menuRepository.delete(menu);
	}

}
