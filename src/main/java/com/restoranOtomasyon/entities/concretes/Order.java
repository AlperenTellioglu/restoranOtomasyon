package com.restoranOtomasyon.entities.concretes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int orderId;
	
	@Column(name = "order_date")
	private LocalDateTime orderDate;
	
	@Column(name = "order_status")
	private String status;
	
	@Column(name = "table_id")
    private int tableId;
	
	@Column(name = "table_number")
	private int tableNumber;
	
	@Column(name = "order_detail_id")
	private List<Integer> orderDetails;
	
	@Column(name = "total_price")
	private double totalPrice;
	
	@Column(name = "total_expense")
	private double totalExpense;
	
	
}
