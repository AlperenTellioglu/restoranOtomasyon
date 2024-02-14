package com.restoranOtomasyon.business.responses;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeyByIdEmployeeResponse {

	private int id;

	private String name;

	private String surname;

	private String userId;

	private String password;

	private String position;

	private String accountType;

	private LocalDateTime startDate;
}
