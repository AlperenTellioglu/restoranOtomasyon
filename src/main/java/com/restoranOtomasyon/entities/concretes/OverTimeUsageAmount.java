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
@Table(name = "over_time_usage_amount")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OverTimeUsageAmount {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int otuaId;
	
	@Column(name = "daily_usage_amount")
	private double dailyUsageAmount;
	
	@Column(name = "weekly_usage_amount")
	private double weeklyUsageAmount;
	
	@Column(name = "monthly_usage_amount")
	private double monthlyUsageAmount;
	
	@Column(name = "product_name")
	private String productName;
	
	@Column(name = "product_id")
	private int productId;
	
	@Column(name = "product_quantity")
	private double productQuantity;
}
