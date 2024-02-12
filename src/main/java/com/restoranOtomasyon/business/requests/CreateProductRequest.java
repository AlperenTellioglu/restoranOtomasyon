package com.restoranOtomasyon.business.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductRequest {

	@NotNull
	@NotBlank
	private String productName;
	
	@NotNull
	@NotBlank
	private double price;
	
	@NotNull
	@NotBlank
	private double quantity;
}
