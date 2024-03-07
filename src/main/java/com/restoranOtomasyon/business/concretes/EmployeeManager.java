package com.restoranOtomasyon.business.concretes;

import org.springframework.stereotype.Service;

import com.restoranOtomasyon.business.abstracts.EmployeeService;
import com.restoranOtomasyon.dataAccess.abstracts.EmployeeRepository;
import com.restoranOtomasyon.entities.concretes.Employee;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class EmployeeManager implements EmployeeService{
	
	private EmployeeRepository employeeRepository;
	
	@Override
	public Employee getEmployeeByUserid(String userId) {
		return employeeRepository.findByUserId(userId);
	}
	
	@Override
	public boolean isValidEmployee(String userId, String password) {
		Employee employee = getEmployeeByUserid(userId);
		return employee != null && employee.getPassword().equals(password);
	}

	

}
