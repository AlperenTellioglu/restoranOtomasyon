package com.restoranOtomasyon.entities.concretes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "waiter_notifications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WaiterNotification {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int waiterNotificationId;
	
	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;
	
	@Column(name = "waiter_notification_status")
	private String status;
}
