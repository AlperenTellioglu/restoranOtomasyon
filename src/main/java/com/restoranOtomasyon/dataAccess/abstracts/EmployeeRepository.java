package com.restoranOtomasyon.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restoranOtomasyon.entities.concretes.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

}
