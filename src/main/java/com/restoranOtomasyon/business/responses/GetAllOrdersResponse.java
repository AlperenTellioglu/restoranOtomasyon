package com.restoranOtomasyon.business.responses;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllOrdersResponse {

	private int id;
	
	private LocalDateTime orderDate;
	
	private String status;
	
	private GetAllCustomerTablesResponse customerTable;
	
	private List<GetAllOrderDetailsResponse> orderDetails;
}
