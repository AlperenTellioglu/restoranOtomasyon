package com.restoranOtomasyon.webApi;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.restoranOtomasyon.business.abstracts.OrderDetailService;
import com.restoranOtomasyon.business.requests.CreateOrderDetailRequest;
import com.restoranOtomasyon.business.responses.GetAllOrderDetailsResponse;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class OrderDetailRestController {

	private OrderDetailService orderDetailService;
	
	@GetMapping("/orderDetails")
	public List<GetAllOrderDetailsResponse> getAll() {
		
		return orderDetailService.getAllOrderDetails();
	}
	
	@PostMapping("/createOrderDetail")
	@ResponseStatus(code = HttpStatus.CREATED)
	public void add(@RequestBody CreateOrderDetailRequest createOrderDetailRequest) {
		
		this.orderDetailService.add(createOrderDetailRequest);
	}
}
