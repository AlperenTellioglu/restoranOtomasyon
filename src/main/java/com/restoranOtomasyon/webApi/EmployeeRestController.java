package com.restoranOtomasyon.webApi;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.restoranOtomasyon.business.abstracts.EmployeeService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class EmployeeRestController {

	private EmployeeService employeeService;

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestParam String userId, @RequestParam String password) {
		if (employeeService.isValidEmployee(userId, password)) {
			return ResponseEntity.ok("Login başarılı");
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Geçersiz giriş");
		}

	}
}
