package com.restoranOtomasyon.business.abstracts;

import java.util.List;

import com.restoranOtomasyon.business.responses.GetAllCustomerTablesResponse;

public interface CustomerTableService {

	//public boolean isEmptyTable(boolean status);
	List<GetAllCustomerTablesResponse> getAllTables();
}
