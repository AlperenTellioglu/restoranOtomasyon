package com.restoranOtomasyon.entities.concretes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "menu")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Menu {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int menuId;
	
	@Column(name = "name")
	private String menuName;
	
	@Column(name = "price")
	private double price;
	
	@Column(name = "expense")
	private double expense;
	
	@Column(name = "ingredients")
	private String ingredients;
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
	
	@OneToOne
	private OrderDetail orderDetail;
}
