package com.restoranOtomasyon.webApi;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.restoranOtomasyon.business.abstracts.OrderService;
import com.restoranOtomasyon.business.requests.CreateOrderRequest;
import com.restoranOtomasyon.business.responses.GetAllCustomerTablesResponse;
import com.restoranOtomasyon.business.responses.GetAllOrdersResponse;
import com.restoranOtomasyon.business.responses.GetByOrderStatusResponse;
import com.restoranOtomasyon.core.utilities.mappers.ModelMapperService;
import com.restoranOtomasyon.dataAccess.abstracts.CustomerTableRepository;
import com.restoranOtomasyon.dataAccess.abstracts.OrderRepository;
import com.restoranOtomasyon.entities.concretes.CustomerTable;
import com.restoranOtomasyon.entities.concretes.Order;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class OrderRestController {

	private OrderService orderService;
	private OrderRepository orderRepository;
	private CustomerTableRepository customerTableRepository;
	private ModelMapperService modelMapperService;

	@GetMapping("/orders")
	public List<GetAllOrdersResponse> getAll() {
		
		return orderService.getAllOrders();
	}
	
//	@PostMapping("/create")
//    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
//        Order createdOrder = orderService.addOrder(order);
//        return ResponseEntity.ok(createdOrder);
//	}
//	
//	@PostMapping("/createOrder")
//	@ResponseStatus(code = HttpStatus.CREATED)
//	public void add(@RequestBody @Valid CreateOrderRequest createOrderRequest) {
//		this.orderService.add(createOrderRequest);
//	}


	@PostMapping("/createOrder")
	public ResponseEntity<Integer> add(CreateOrderRequest orderRequest) {
	    Order order = new Order();
	    
	    CustomerTable table = customerTableRepository.findById(orderRequest.getTableId())
	            .orElseThrow();
	    
	    order.setTableId(table.getTableId());
	    order.setTableNumber(table.getTableNumber());
	    order.setTotalPrice(0);
	    order.setTotalExpense(0);
	    order.setOrderDate(LocalDateTime.now());
	    order.setStatus("Hazırlanıyor");
	    
	    table.setStatus("DOLU");
	    
	    this.customerTableRepository.save(table);
	    this.orderRepository.save(order);
	    
	    return ResponseEntity.ok(order.getOrderId());
	}
	
	@GetMapping("/getOrdersByStatus")
	public List<GetByOrderStatusResponse> getOrdersByStatus() {

		List<Order> orders = orderRepository.findAll();
		
		List<GetByOrderStatusResponse> statusResponse = orders.stream()
				.map(order -> this.modelMapperService.forResponse()
						.map(order, GetByOrderStatusResponse.class)).collect(Collectors.toList());
		
		return statusResponse;
	} 

}
