package com.restoranOtomasyon.entities.concretes;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tables")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerTable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int tableId;
	
	@Column(name = "table_number")
	private String tableNumber;
	
	@Column(name = "table_status")
	private boolean status;
	
	@OneToOne(mappedBy = "customerTable", cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
	private Order order;
}
