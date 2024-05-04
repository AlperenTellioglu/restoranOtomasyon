package com.restoranOtomasyon.webApi;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restoranOtomasyon.business.abstracts.OrderDetailService;
import com.restoranOtomasyon.business.requests.CreateOrderDetailRequest;
import com.restoranOtomasyon.business.responses.GetAllOrderDetailsResponse;
import com.restoranOtomasyon.dataAccess.abstracts.MenuRepository;
import com.restoranOtomasyon.dataAccess.abstracts.OrderDetailRepository;
import com.restoranOtomasyon.dataAccess.abstracts.OrderRepository;
import com.restoranOtomasyon.entities.concretes.Menu;
import com.restoranOtomasyon.entities.concretes.Order;
import com.restoranOtomasyon.entities.concretes.OrderDetail;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class OrderDetailRestController {

	private OrderDetailService orderDetailService;
	private OrderRepository orderRepository;
	private MenuRepository menuRepository;
	private OrderDetailRepository orderDetailRepository;
	
	@GetMapping("/orderDetails")
	public List<GetAllOrderDetailsResponse> getAll() {
		
		return orderDetailService.getAllOrderDetails();
	}
	
	@PostMapping("/createOrderDetails")
	public void add(CreateOrderDetailRequest orderDetailRequest) {
		OrderDetail orderDetail = new OrderDetail();
		
		Order order = orderRepository.findById(orderDetailRequest.getOrderId())
				.orElseThrow(() -> new RuntimeException("Siparis bulunamadı"));
		
		orderDetail.setOrderId(order.getOrderId());
		
		if (order.getOrderDetails() == null) {
		    order.setOrderDetails(new ArrayList<>());
		}
		order.getOrderDetails().add(orderDetail.getOrderDetailId());

		
		Menu menu = menuRepository.findById(orderDetailRequest.getMenuId())
				.orElseThrow();
		
		
		orderDetail.setMenuId(menu.getMenuId());
		orderDetail.setQuantity(orderDetailRequest.getQuantity());
		orderDetail.setUnitPrice(menu.getPrice());
		orderDetail.setMenuName(menu.getMenuName());
		orderDetail.setTotalPrice(menu.getPrice()* orderDetail.getQuantity());
		orderDetail.setExpense(menu.getExpense() * orderDetail.getQuantity());
		
		order.setTotalPrice(orderDetail.getTotalPrice()+ order.getTotalPrice());
		order.setTotalExpense(orderDetail.getExpense() + order.getTotalExpense());
		
		orderDetail.setOrderNote(orderDetailRequest.getOrderNote());
		
		this.orderDetailRepository.save(orderDetail);
		this.orderRepository.save(order);
		
	}
}
