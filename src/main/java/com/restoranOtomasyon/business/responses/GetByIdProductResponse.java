package com.restoranOtomasyon.business.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetByIdProductResponse {

	private int id;

	private String name;

	private int quantity;

	private double price;
}
