package com.restoranOtomasyon.webApi;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.restoranOtomasyon.business.abstracts.OrderService;
import com.restoranOtomasyon.business.requests.ChanceOrderStatusRequest;
import com.restoranOtomasyon.business.requests.CreateOrderRequest;
import com.restoranOtomasyon.business.responses.GetAllOrdersResponse;
import com.restoranOtomasyon.business.responses.GetByOrderStatusResponse;
import com.restoranOtomasyon.business.responses.GetByTableNubmerOrderResponse;
import com.restoranOtomasyon.business.responses.GetOrdersForTable;
import com.restoranOtomasyon.core.utilities.mappers.ModelMapperService;
import com.restoranOtomasyon.dataAccess.abstracts.CustomerTableRepository;
import com.restoranOtomasyon.dataAccess.abstracts.OrderDetailRepository;
import com.restoranOtomasyon.dataAccess.abstracts.OrderRepository;
import com.restoranOtomasyon.entities.concretes.CustomerTable;
import com.restoranOtomasyon.entities.concretes.Order;
import com.restoranOtomasyon.entities.concretes.OrderDetail;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class OrderRestController {

	private OrderService orderService;
	private OrderRepository orderRepository;
	private CustomerTableRepository customerTableRepository;
	private OrderDetailRepository orderDetailRepository;
	private ModelMapperService modelMapperService;

	@GetMapping("/orders")
	public List<GetAllOrdersResponse> getAll() {

		return orderService.getAllOrders();
	}

	@PostMapping("/createOrder")
	public ResponseEntity<Integer> add(CreateOrderRequest orderRequest) {
		Order order = new Order();

		CustomerTable table = customerTableRepository.findById(orderRequest.getTableId()).orElseThrow();

		order.setTableId(table.getTableId());
		order.setTableNumber(table.getTableNumber());
		order.setTotalPrice(0);
		order.setTotalExpense(0);
		order.setOrderDate(LocalDateTime.now());
		order.setStatus("hazirlaniyor");

		table.setStatus("DOLU");

		this.customerTableRepository.save(table);
		this.orderRepository.save(order);

		return ResponseEntity.ok(order.getOrderId());
	}

	@GetMapping("/getOrdersByStatus")
	public List<GetByOrderStatusResponse> getOrdersByStatus() {

	    List<Order> orders = orderRepository.findAll();

	    List<GetByOrderStatusResponse> statusResponse = orders.stream()
	            .sorted(Comparator.comparing(Order::getStatus, Comparator.reverseOrder()))
	            .map(order -> modelMapperService.forResponse().map(order, GetByOrderStatusResponse.class))
	            .collect(Collectors.toList());

	    return statusResponse;
	}



	@GetMapping("/getByTableNumberOrder")
	public List<GetByTableNubmerOrderResponse> getOrder() {
		List<Order> orders = orderRepository.findAll();

		List<GetByTableNubmerOrderResponse> tableNoResponse = orders.stream()
				.map(order -> this.modelMapperService.forResponse().map(order, GetByTableNubmerOrderResponse.class))
				.collect(Collectors.toList());

		return tableNoResponse;

	}

	@PostMapping("/dontShowOrder")
	public void dontShowOrder(ChanceOrderStatusRequest chanceRequest) {
		Order order = orderRepository.findById(chanceRequest.getOrderId()).orElseThrow();

		if (order.getStatus().equals("iptal")) {
			order.setStatus("gosterme");
		}

		else if (order.getStatus().equals("onaylandi")) {
			order.setStatus("gostermeonay");
		}

		this.orderRepository.save(order);

	}

	@PostMapping("/confrimOrder")
	public void confrimOrder(ChanceOrderStatusRequest chanceRequest) {
		Order order = orderRepository.findById(chanceRequest.getOrderId()).orElseThrow();

		order.setStatus("onaylandi");

		this.orderRepository.save(order);

	}

	@PostMapping("/cancelOrder")
	public void cancelOrder(ChanceOrderStatusRequest chanceRequest) {
		Order order = orderRepository.findById(chanceRequest.getOrderId()).orElseThrow();

		order.setStatus("iptal");
		
		this.orderRepository.save(order);
		
		List<Order> orders = orderRepository.findByTableId(order.getTableId());
		
		CustomerTable table = customerTableRepository.findById(order.getTableId())
				.orElseThrow();
		
		int count = 0;
		
		for(Order item : orders) {
			if(!item.getStatus().equals("iptal")) {
				count += 1;
			}
		}
		
		if(count == 0) {
			table.setStatus("BOŞ");
			this.customerTableRepository.save(table);
		}
		

	}

	@GetMapping("/orders/{tableId}")
	public ResponseEntity<List<GetOrdersForTable>> getOrdersForTable(@PathVariable int tableId) {
	    List<Order> orders = orderRepository.findByTableId(tableId);

	    List<GetOrdersForTable> orderResponses = new ArrayList<>();

	    for (Order order : orders) {
	        GetOrdersForTable orderResponse = new GetOrdersForTable();
	        orderResponse.setOrderId(order.getOrderId());
	        orderResponse.setTableId(order.getTableId());
	        orderResponse.setStatus(order.getStatus());
	        
	        List<OrderDetail> orderDetails = orderDetailRepository.findByOrderId(order.getOrderId());
	        double totalPrice = orderDetails.stream()
	                .mapToDouble(detail -> detail.getTotalPrice())
	                .sum();
	        double totalExpense = orderDetails.stream()
	                .mapToDouble(detail -> detail.getExpense())
	                .sum();
	        
	        orderResponse.setTotalPrice(totalPrice);
	        orderResponse.setTotalExpense(totalExpense);
	        orderResponse.setOrderDetails(orderDetails);

	        orderResponses.add(orderResponse);
	    }

	    return ResponseEntity.ok(orderResponses);
	}


	@PutMapping("/move/all")
	public ResponseEntity<String> moveAllOrdersToTable(@RequestParam int sourceTableId,
			@RequestParam int targetTableId) {
		List<Order> orders = orderRepository.findByTableId(sourceTableId);
		CustomerTable customerTable = customerTableRepository.findById(targetTableId).orElseThrow();
		CustomerTable customerTable2 = customerTableRepository.findById(sourceTableId).orElseThrow();
		if (!orders.isEmpty()) {
			for (Order order : orders) {
				order.setTableId(customerTable.getTableId());
				order.setTableNumber(customerTable.getTableNumber());
				orderRepository.save(order);
			}
			customerTable.setStatus("DOLU");
			customerTable2.setStatus("BOŞ");
			customerTableRepository.save(customerTable);
			customerTableRepository.save(customerTable2);
			return ResponseEntity.ok("All orders moved successfully to table " + targetTableId);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/combineTables")
	public ResponseEntity<String> combineTables(@RequestParam int sourceTableId, @RequestParam int targetTableId) {
		List<Order> orders = orderRepository.findByTableId(sourceTableId);
		CustomerTable customerTable = customerTableRepository.findById(targetTableId).orElseThrow();
		CustomerTable customerTable2 = customerTableRepository.findById(sourceTableId).orElseThrow();

		if (!orders.isEmpty()) {
			for (Order order : orders) {
				order.setTableId(customerTable.getTableId());
				order.setTableNumber(customerTable.getTableNumber());
				orderRepository.save(order);
			}
			customerTable.setStatus("DOLU");
			customerTable2.setStatus("BOŞ");
			customerTableRepository.save(customerTable);
			customerTableRepository.save(customerTable2);
			return ResponseEntity.ok("All orders moved successfully to table " + targetTableId);
		} else {
			return ResponseEntity.notFound().build();

		}

	}

}
