package com.restoranOtomasyon.business.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllOrderDetailsResponse {

	private int id;
	
	private int quantity;
	
	private double totalPrice;
	
	private double unitPrice;
	
	private int orderId;
	
	private String menuName;
}
