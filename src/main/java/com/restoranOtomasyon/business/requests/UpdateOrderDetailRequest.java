package com.restoranOtomasyon.business.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateOrderDetailRequest {

	private int id;
	
	private int quantity;
	
	private double totalPrice;
	
	private int menuId;
}
