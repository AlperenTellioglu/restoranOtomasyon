package com.restoranOtomasyon.webApi;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.restoranOtomasyon.business.abstracts.OrderService;
import com.restoranOtomasyon.business.requests.CreateOrderRequest;
import com.restoranOtomasyon.business.responses.GetAllOrdersResponse;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class OrderRestController {

	private OrderService orderService;

	@GetMapping("/orders")
	public List<GetAllOrdersResponse> getAll() {
		
		return orderService.getAllOrders();
	}
	
	@PostMapping("/createOrder")
	@ResponseStatus(code = HttpStatus.CREATED)
	public void add(@RequestBody @Valid CreateOrderRequest createOrderRequest) {
		
		this.orderService.add(createOrderRequest);
	}
}
