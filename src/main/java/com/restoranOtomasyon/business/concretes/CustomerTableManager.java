package com.restoranOtomasyon.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.restoranOtomasyon.business.abstracts.CustomerTableService;
import com.restoranOtomasyon.business.responses.GetAllCustomerTablesResponse;
import com.restoranOtomasyon.core.utilities.mappers.ModelMapperService;
import com.restoranOtomasyon.dataAccess.abstracts.CustomerTableRepository;
import com.restoranOtomasyon.entities.concretes.CustomerTable;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomerTableManager implements CustomerTableService {

	private CustomerTableRepository customerTableRepository;
	private ModelMapperService modelMapperService;

//	@Override
//	public boolean isEmptyTable(boolean status) {
//		// TODO Auto-generated method stub
//		return false;
//	}

	@Override
	public List<GetAllCustomerTablesResponse> getAllTables() {
		
		List<CustomerTable> tables = customerTableRepository.findAll(Sort.by(Sort.Direction.ASC, "tableNumber"));
		
		List<GetAllCustomerTablesResponse> tablesResponse = tables.stream()
				.map(table ->this.modelMapperService.forResponse()
						.map(table, GetAllCustomerTablesResponse.class)).collect(Collectors.toList());
		
		return tablesResponse;
	}

}
