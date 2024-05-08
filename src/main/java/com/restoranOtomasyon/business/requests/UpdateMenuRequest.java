package com.restoranOtomasyon.business.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateMenuRequest {

	private int id;
	
	private String name;
	
	private double price;
	
	private double expense;
	
	private int categoryId;

}
