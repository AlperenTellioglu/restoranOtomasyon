package com.restoranOtomasyon.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.restoranOtomasyon.business.abstracts.OrderDetailService;
import com.restoranOtomasyon.business.requests.CreateOrderDetailRequest;
import com.restoranOtomasyon.business.responses.GetAllOrderDetailsResponse;
import com.restoranOtomasyon.core.utilities.mappers.ModelMapperService;
import com.restoranOtomasyon.dataAccess.abstracts.OrderDetailRepository;
import com.restoranOtomasyon.entities.concretes.OrderDetail;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrderDetailManager implements OrderDetailService{
	
	private OrderDetailRepository orderDetailRepository;
	private ModelMapperService modelMapperService;
	
	@Override
	public List<GetAllOrderDetailsResponse> getAllOrderDetails() {
		List<OrderDetail> orderDetails = orderDetailRepository.findAll();
		
		List<GetAllOrderDetailsResponse> orderDetailResponse = orderDetails.stream()
				.map(orderDetail -> this.modelMapperService.forResponse()
						.map(orderDetail, GetAllOrderDetailsResponse.class)).collect(Collectors.toList());
		
		return orderDetailResponse;
	}

	@Override
	public void add(CreateOrderDetailRequest createOrderDetailRequest) {
		OrderDetail orderDetail = this.modelMapperService.forRequest().map(createOrderDetailRequest, OrderDetail.class);
		
		this.orderDetailRepository.save(orderDetail);
		
	}

}
