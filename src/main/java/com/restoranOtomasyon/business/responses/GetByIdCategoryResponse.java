package com.restoranOtomasyon.business.responses;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetByIdCategoryResponse {
	
	private int id;
	
	private String name;
	
	private List<GetAllMenusResponse> menus;
}
