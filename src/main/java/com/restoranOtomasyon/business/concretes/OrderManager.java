package com.restoranOtomasyon.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.restoranOtomasyon.business.abstracts.OrderService;
import com.restoranOtomasyon.business.responses.GetAllOrdersResponse;
import com.restoranOtomasyon.core.utilities.mappers.ModelMapperService;
import com.restoranOtomasyon.dataAccess.abstracts.OrderRepository;
import com.restoranOtomasyon.entities.concretes.Order;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrderManager implements OrderService{

	private OrderRepository orderRepository;
	private ModelMapperService modelMapperService;
	
	@Override
	public List<GetAllOrdersResponse> getAllOrders() {
		List<Order> orders = orderRepository.findAll();
		
		List<GetAllOrdersResponse> ordersResponse = orders.stream()
				.map(order -> this.modelMapperService.forResponse()
						.map(order, GetAllOrdersResponse.class)).collect(Collectors.toList());
		
		return ordersResponse;
	}
//	@Override
//	public void add(CreateOrderRequest createOrderRequest) {
//		CustomerTable table = customerTableRepository.findById(createOrderRequest.getTableId())
//				.orElseThrow(() -> new RuntimeException("Masa bulunamadı"));
//		
//		Order order = new Order();
//		order.setTable(table);
//		order.setOrderDate(LocalDateTime.now());
//		order.setStatus("Hazırlanıyor");
//		
//		this.orderRepository.save(order);
//		
//	}
//	
//	@Override
//	public Order addOrder(Order order) {
//		return this.orderRepository.save(order);
//	}
}
