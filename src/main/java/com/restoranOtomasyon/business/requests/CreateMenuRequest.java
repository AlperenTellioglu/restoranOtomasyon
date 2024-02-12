package com.restoranOtomasyon.business.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateMenuRequest {

	@NotNull
	@NotBlank
	private String name;
	
	@NotNull
	@NotBlank
	private double price;
	
	@NotNull
	@NotBlank
	private int categoryId;
}
