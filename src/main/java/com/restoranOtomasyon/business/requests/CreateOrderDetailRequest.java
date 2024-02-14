package com.restoranOtomasyon.business.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderDetailRequest {

	private int quantity;
	
	private double totalPrice;
	
	private int orderId;
	
	private int productId;
}
