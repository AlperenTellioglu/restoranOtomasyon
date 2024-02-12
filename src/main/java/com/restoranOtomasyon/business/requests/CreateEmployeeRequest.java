package com.restoranOtomasyon.business.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateEmployeeRequest {

	@NotNull
	@NotBlank
	private String name;
	
	@NotNull
	@NotBlank
	private String surname;
	
	@NotNull
	@NotBlank
	private String position;
	
	@NotNull
	@NotBlank
	private String userId;
	
	@NotNull
	@NotBlank
	private String password;
	
	@NotNull
	@NotBlank
	private String accountType;
	
}
