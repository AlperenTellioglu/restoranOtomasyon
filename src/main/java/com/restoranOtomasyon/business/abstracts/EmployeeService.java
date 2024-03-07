package com.restoranOtomasyon.business.abstracts;

import com.restoranOtomasyon.entities.concretes.Employee;

public interface EmployeeService {
	public boolean isValidEmployee(String username, String password);
	public Employee getEmployeeByUserid(String userId);
}
