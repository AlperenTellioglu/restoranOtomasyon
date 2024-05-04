package com.restoranOtomasyon.business.abstracts;

import java.util.List;

import com.restoranOtomasyon.business.requests.CreateOrderRequest;
import com.restoranOtomasyon.business.responses.GetAllOrdersResponse;
import com.restoranOtomasyon.entities.concretes.Order;

public interface OrderService {

	public List<GetAllOrdersResponse> getAllOrders();
//	public void add(CreateOrderRequest createOrderRequest);
//	public Order addOrder(Order order);
}
