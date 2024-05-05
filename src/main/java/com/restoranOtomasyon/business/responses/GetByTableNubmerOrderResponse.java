package com.restoranOtomasyon.business.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetByTableNubmerOrderResponse {

	private int orderId;
	
	private int tableId;
	
	private int tableNumber;
}
