package com.restoranOtomasyon.business.responses;

import com.restoranOtomasyon.entities.concretes.Order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllCustomerTablesResponse {

	private int id;
	
	private int tableNumber;
	
	private String status;
	
	private Order order;
}
