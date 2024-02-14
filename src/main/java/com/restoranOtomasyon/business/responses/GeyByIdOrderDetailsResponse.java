package com.restoranOtomasyon.business.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeyByIdOrderDetailsResponse {

	private int id;

	private int quantity;

	private double totalPrice;

	private GetAllOrdersResponse orders;

	private GetAllMenusResponse menus;
}
