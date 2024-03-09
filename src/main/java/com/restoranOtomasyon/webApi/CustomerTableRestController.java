package com.restoranOtomasyon.webApi;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restoranOtomasyon.business.abstracts.CustomerTableService;
import com.restoranOtomasyon.business.responses.GetAllCustomerTablesResponse;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class CustomerTableRestController {

	private CustomerTableService tableService;

	@GetMapping("/tables")
	public List<GetAllCustomerTablesResponse> getAllTables() {

		return tableService.getAllTables();
	}
}
