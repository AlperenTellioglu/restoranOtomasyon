package com.restoranOtomasyon.business.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateEmployeeRequest {

	private int id;
	
	private String position;
	
	private String userId;
	
	private String password;
	
	private String accountType;
	
	
}
