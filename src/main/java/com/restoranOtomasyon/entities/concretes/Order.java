package com.restoranOtomasyon.entities.concretes;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
	
//	@OneToOne
//    private CustomerTable customerTable;
	
	@OneToMany(mappedBy = "order")
	private List<OrderDetail> orderDetails;
}
