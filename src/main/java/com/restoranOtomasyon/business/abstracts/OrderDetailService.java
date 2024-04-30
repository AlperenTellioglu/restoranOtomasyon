package com.restoranOtomasyon.business.abstracts;

import java.util.List;

import com.restoranOtomasyon.business.requests.CreateOrderDetailRequest;
import com.restoranOtomasyon.business.responses.GetAllOrderDetailsResponse;

public interface OrderDetailService {

	public List<GetAllOrderDetailsResponse> getAllOrderDetails();
	public void add(CreateOrderDetailRequest createOrderDetailRequest);
}
