package com.restoranOtomasyon.webApi;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restoranOtomasyon.business.responses.GetProductsWithUAResponse;
import com.restoranOtomasyon.core.utilities.mappers.ModelMapperService;
import com.restoranOtomasyon.dataAccess.abstracts.OTUARepository;
import com.restoranOtomasyon.entities.concretes.OverTimeUsageAmount;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class OverTimeUsageAmountRestController {

	private OTUARepository otuaRepository;
	private ModelMapperService modelMapperService;
	
	
	@GetMapping("/getProductsWithUsageAmount")
	public List<GetProductsWithUAResponse> getProductsWithUA() {
		List<OverTimeUsageAmount> otuas = otuaRepository.findAll();

		List<GetProductsWithUAResponse> otuaResponse = otuas.stream()
				.map(otua -> this.modelMapperService.forResponse()
						.map(otua, GetProductsWithUAResponse.class)).collect(Collectors.toList());

		return otuaResponse;
	}
}
