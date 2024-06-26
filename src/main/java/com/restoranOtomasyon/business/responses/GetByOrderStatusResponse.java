package com.restoranOtomasyon.business.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetByOrderStatusResponse {

	private int orderId;
	
	private int tableNumber;
	
	private String status;
}
