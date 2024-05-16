package com.restoranOtomasyon.webApi;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.restoranOtomasyon.business.abstracts.EmployeeService;
import com.restoranOtomasyon.business.requests.CreateEmployeeRequest;
import com.restoranOtomasyon.business.responses.GetAllEmployeesResponse;
import com.restoranOtomasyon.dataAccess.abstracts.EmployeeRepository;
import com.restoranOtomasyon.entities.concretes.Employee;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class EmployeeRestController {

	private EmployeeService employeeService;
	private EmployeeRepository employeeRepository;

	@GetMapping("/employees")
	public List<GetAllEmployeesResponse> getEmployees(){
		
		List<Employee> employees = employeeRepository.findAll();
		
//		List<GetAllEmployeesResponse> employeeResponses = employees.stream()
//				.map(employee -> this.modelMapperService.forResponse()
//						.map(employee, GetAllEmployeesResponse.class)).collect(Collectors.toList());
		
		List<GetAllEmployeesResponse> employeeResponses = new ArrayList<GetAllEmployeesResponse>();
		
		for(Employee employee : employees) {
			GetAllEmployeesResponse responseItem = new GetAllEmployeesResponse();
			responseItem.setId(employee.getEmployeeId());
			responseItem.setName(employee.getName());
			responseItem.setSurname(employee.getSurname());
			responseItem.setUserId(employee.getUserId());
			responseItem.setPassword(employee.getPassword());
			responseItem.setPosition(employee.getPosition());
			responseItem.setStartDate(employee.getStartDate());
			responseItem.setAccountType(employee.getAccountType());
			
			employeeResponses.add(responseItem);
		}
		
		return employeeResponses;
	}
	
	
	@PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String userId, @RequestParam String password) {
        if (employeeService.isValidEmployee(userId, password)) {
            Employee employee = employeeService.getEmployeeByUserid(userId);
            return ResponseEntity.ok("Login başarılı, AccountType: " + employee.getAccountType());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Geçersiz giriş");
        }
    }
	
	
	@PostMapping("/createEmployee")
	public void addEmployee(CreateEmployeeRequest employeeRequest) {
		
		Employee employee = new Employee();
		
		employee.setName(employeeRequest.getName());
		employee.setSurname(employeeRequest.getSurname());
		employee.setAccountType(employeeRequest.getAccountType());
		employee.setPosition(employeeRequest.getPosition());
		employee.setStartDate(LocalDateTime.now());
		employee.setUserId(employeeRequest.getUserId());
		employee.setPassword(employeeRequest.getPassword());
		
		this.employeeRepository.save(employee);
			
	}
	
	@DeleteMapping("/deleteEmployee")
	public void deleteEmployee(int empId) {
		Employee employee = employeeRepository.findById(empId)
				.orElseThrow();
		
		this.employeeRepository.delete(employee);
		
	}
	
	
	
}
