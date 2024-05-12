package com.restoranOtomasyon.business.responses;

import java.util.List;

import com.restoranOtomasyon.entities.concretes.OrderDetail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetOrdersForTable {

	private int orderId;
	
	private int tableId;
	
	private String status;
	
	private double totalIncome;
	
	private double totalExpense;
	
	private List<OrderDetail> orderDetails;
}
