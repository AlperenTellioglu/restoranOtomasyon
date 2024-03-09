package com.restoranOtomasyon.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.restoranOtomasyon.business.abstracts.OrderService;
import com.restoranOtomasyon.business.requests.CreateOrderRequest;
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
	@Override
	public void add(CreateOrderRequest createOrderRequest) {
		Order order = this.modelMapperService.forRequest().map(createOrderRequest, Order.class);
		
		this.orderRepository.save(order);
		
	}
	
	
}
