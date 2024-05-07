package com.restoranOtomasyon.entities.concretes;

import java.time.YearMonth;

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
@Table(name = "monthly_profit")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MonthlyProfit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "expense")
	private double expense;
	
	@Column(name = "income")
	private double income;
	
	@Column(name = "profit")
	private double profit;
	
	@Column(name = "date")
	private YearMonth month;
}
