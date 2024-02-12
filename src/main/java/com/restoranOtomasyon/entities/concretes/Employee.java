package com.restoranOtomasyon.entities.concretes;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int employeeId;
	
	@Column(name = "employee_name")
	private String name;
	
	@Column(name = "employee_surname")
	private String surname;
	
	@Column(name = "employee_position")
	private String position;
	
	@Column(name = "start_date")
	private LocalDateTime startDate;
}
