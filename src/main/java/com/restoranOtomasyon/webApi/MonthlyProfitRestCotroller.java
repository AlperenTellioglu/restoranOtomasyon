package com.restoranOtomasyon.webApi;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restoranOtomasyon.dataAccess.abstracts.MonthlyProfitRepository;
import com.restoranOtomasyon.entities.concretes.MonthlyProfit;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class MonthlyProfitRestCotroller {

	private  MonthlyProfitRepository monthlyProfitRepository;
	
	
	@GetMapping("/monthly-profit/last-12-months")
	public ResponseEntity<List<MonthlyProfit>> getLast12MonthsProfits() {
	    List<MonthlyProfit> last12MonthsProfits = monthlyProfitRepository.findTop12ByOrderByMonthDesc();
	    if (!last12MonthsProfits.isEmpty()) {
	        return ResponseEntity.ok(last12MonthsProfits);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
}
