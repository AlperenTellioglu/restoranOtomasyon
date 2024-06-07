package com.restoranOtomasyon.webApi;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.restoranOtomasyon.business.responses.GetAllProcutsResponse;
import com.restoranOtomasyon.core.utilities.mappers.ModelMapperService;
import com.restoranOtomasyon.dataAccess.abstracts.OTUARepository;
import com.restoranOtomasyon.dataAccess.abstracts.ProductRepository;
import com.restoranOtomasyon.dataAccess.abstracts.UsageAmountRepository;
import com.restoranOtomasyon.entities.concretes.OverTimeUsageAmount;
import com.restoranOtomasyon.entities.concretes.Product;
import com.restoranOtomasyon.entities.concretes.UsageAmount;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class ProductRestController {

	private ProductRepository productRepository;
	private UsageAmountRepository usageAmountRepository;
	private OTUARepository overTimeUsageAmountRepository;
	private ModelMapperService modelMapperService;

	@GetMapping("/getProducts")
	public List<GetAllProcutsResponse> getAllProducts() {
		List<Product> products = productRepository.findAll();

		List<GetAllProcutsResponse> productsResponse = products.stream()
				.map(product -> this.modelMapperService.forResponse()
						.map(product, GetAllProcutsResponse.class)).collect(Collectors.toList());

		return productsResponse;
	}

	@PostMapping("/increaseProductQuantity")
	public void increaseProductQuantity(@RequestParam int productId, @RequestParam double amount) {
		Product product = productRepository.findById(productId)
				.orElseThrow();
		
		product.setQuantity(product.getQuantity() + amount);
		productRepository.save(product);
		
		Optional<OverTimeUsageAmount> existingOverTimeUsageAmount = overTimeUsageAmountRepository
				.findByProductId(productId);
		OverTimeUsageAmount otua;
		if(existingOverTimeUsageAmount.isPresent()) {
			otua = existingOverTimeUsageAmount.get();
			otua.setProductQuantity(product.getQuantity());
			
			this.overTimeUsageAmountRepository.save(otua);
		}
		
		
	}
	
	
	@PostMapping("/reduceProductQuantity")
	public void updateProductQuantity(@RequestParam int productId, @RequestParam double usageAmount) {

		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));

		product.setQuantity(product.getQuantity() - usageAmount);
		productRepository.save(product);

		Optional<UsageAmount> existingUsageAmount = usageAmountRepository.findByProductId(product.getProductId());
		UsageAmount usageAmount2;
		if (existingUsageAmount.isPresent()) {
			usageAmount2 = existingUsageAmount.get();
			usageAmount2.setNumberOfDays(usageAmount2.getNumberOfDays() + 1);
			usageAmount2.setUsageAmount(usageAmount2.getUsageAmount() + usageAmount);
		} else {
			usageAmount2 = new UsageAmount();
			usageAmount2.setNumberOfDays(1);
			usageAmount2.setUsageAmount(usageAmount);
			usageAmount2.setProductId(product.getProductId());
		}
		usageAmountRepository.save(usageAmount2);

		Optional<OverTimeUsageAmount> existingOverTimeUsageAmount = overTimeUsageAmountRepository
				.findByProductId(productId);
		OverTimeUsageAmount otua;
		if (existingOverTimeUsageAmount.isPresent()) {
			otua = existingOverTimeUsageAmount.get();
		} else {
			otua = new OverTimeUsageAmount();
			otua.setProductId(product.getProductId());
			otua.setProductName(product.getProductName());
			otua.setProductQuantity(product.getQuantity());
		}
		
		
		otua.setDailyUsageAmount(usageAmount2.getUsageAmount() / usageAmount2.getNumberOfDays());
		otua.setWeeklyUsageAmount(usageAmount2.getUsageAmount() / usageAmount2.getNumberOfDays() * 7);
		otua.setMonthlyUsageAmount(usageAmount2.getUsageAmount() / usageAmount2.getNumberOfDays() * 30);
		otua.setProductQuantity(product.getQuantity());
		otua.setEstEndDay((int) (otua.getProductQuantity() / otua.getDailyUsageAmount()));

		overTimeUsageAmountRepository.save(otua);
	}
	
	@PostMapping("/createProduct")
	public void addProduct(@RequestParam String productName, @RequestParam double quantity) {
		Product product = new Product();
		
		product.setProductName(productName);
		product.setQuantity(quantity);
		
		this.productRepository.save(product);
		
		OverTimeUsageAmount otua = new OverTimeUsageAmount();
		otua.setProductId(product.getProductId());
		otua.setProductName(product.getProductName());
		otua.setDailyUsageAmount(0);
		otua.setEstEndDay(0);
		otua.setMonthlyUsageAmount(0);
		otua.setProductQuantity(product.getQuantity());
		otua.setWeeklyUsageAmount(0);
		
		this.overTimeUsageAmountRepository.save(otua);
	}
	
	@DeleteMapping("/deleteProduct")
	public void deleteProduct(@RequestParam int productId) {
	    
	    usageAmountRepository.findByProductId(productId)
	            .ifPresent(ua -> this.usageAmountRepository.delete(ua));
	    
	    overTimeUsageAmountRepository.findByProductId(productId)
	            .ifPresent(otua -> this.overTimeUsageAmountRepository.delete(otua));
	    
	    
	    Product product = productRepository.findById(productId)
	            .orElseThrow();
	    
	    this.productRepository.delete(product);
	}
	
	
}
