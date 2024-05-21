package com.restoranOtomasyon.business.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetProductsWithUAResponse {

private int otuaId;
	
	private double dailyUsageAmount;

	private double weeklyUsageAmount;

	private double monthlyUsageAmount;

	private String productName;

	private int productId;

	private double productQuantity;

	private int estEndDay;
}
