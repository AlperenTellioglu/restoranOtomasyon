package com.restoranOtomasyon.entities.concretes;

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
@Table(name = "kitchen_notifications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KitchenNotification {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int notificationId;
	
	@OneToOne
	@JoinColumn(name = "order_detail_id", unique = true)
	private OrderDetail orderDetail;
	
	@Column(name = "kitchen_notification_status")
	private String status;
}
