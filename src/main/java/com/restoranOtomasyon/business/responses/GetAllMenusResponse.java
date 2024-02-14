package com.restoranOtomasyon.business.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllMenusResponse {

	private int id;
	
	private String name;
	
	private double price;
	
	private double expense;
	
	private String ingredients;
	
	private int categoryName;
}
