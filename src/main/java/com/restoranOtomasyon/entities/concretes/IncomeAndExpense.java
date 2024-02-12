package com.restoranOtomasyon.entities.concretes;

import java.time.LocalDate;

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
@Table(name="income_and_expense")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IncomeAndExpense {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int ieId;
	
	@Column(name="income")
	private int income;
	
	@Column(name="expense")
	private int expense;
	
	@Column(name="profit")
	private int profit;
	
	@Column(name="date")
	private LocalDate date;
}
