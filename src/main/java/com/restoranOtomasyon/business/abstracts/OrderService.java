package com.restoranOtomasyon.business.abstracts;

import java.util.List;

import com.restoranOtomasyon.business.requests.CreateOrderRequest;
import com.restoranOtomasyon.business.responses.GetAllOrdersResponse;

public interface OrderService {

	public List<GetAllOrdersResponse> getAllOrders();
	public void add(CreateOrderRequest createOrderRequest);
}
