package com.restoranOtomasyon.business.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCustomerTableRequest {

	private int id;
	
	private String tableNumber;
	
	private String status;

}
