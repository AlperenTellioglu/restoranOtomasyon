package com.restoranOtomasyon.entities.concretes;

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
@Table(name = "usage_amount")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsageAmount {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int uaId;
	
	@Column(name = "usage_amount")
	private double usageAmount;
	
	@Column(name = "product_id")
	private int productId;
	
	@Column(name = "number_of_days")
	private int numberOfDays;
	
}
