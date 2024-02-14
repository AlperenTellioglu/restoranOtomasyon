package com.restoranOtomasyon.business.requests;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderRequest {

	private LocalDateTime orderDate;
	
	private String status;
	
	private int tableId;
	
	private List<CreateOrderDetailRequest> orderDetails;
}
