package com.restoranOtomasyon.webApi;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restoranOtomasyon.business.abstracts.CustomerTableService;
import com.restoranOtomasyon.business.responses.GetAllCustomerTablesResponse;
import com.restoranOtomasyon.dataAccess.abstracts.CustomerTableRepository;
import com.restoranOtomasyon.dataAccess.abstracts.OrderDetailRepository;
import com.restoranOtomasyon.dataAccess.abstracts.OrderRepository;
import com.restoranOtomasyon.entities.concretes.CustomerTable;
import com.restoranOtomasyon.entities.concretes.Order;
import com.restoranOtomasyon.entities.concretes.OrderDetail;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class CustomerTableRestController {

	private CustomerTableService tableService;
	private CustomerTableRepository customerTableRepository;
	private OrderDetailRepository orderDetailRepository;
	private OrderRepository orderRepository;

	@GetMapping("/tables")
	public List<GetAllCustomerTablesResponse> getAllTables() {

		return tableService.getAllTables();
	}
	
	@PostMapping("/closeTable")
	public void closeTable(int tableId) {
        List<Order> orders = orderRepository.findByTableId(tableId);
        for (Order order : orders) {
            List<OrderDetail> orderDetails = orderDetailRepository.findByOrderId(order.getOrderId());
            for (OrderDetail orderDetail : orderDetails) {
                orderDetailRepository.delete(orderDetail);
            }
            orderRepository.delete(order);
            
            CustomerTable customerTable = customerTableRepository.findById(tableId)
            		.orElseThrow();
            
            customerTable.setStatus("BOŞ");
            
            this.customerTableRepository.save(customerTable);
        }
    }
}
