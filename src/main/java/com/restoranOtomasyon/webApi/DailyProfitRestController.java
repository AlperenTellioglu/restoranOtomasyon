package com.restoranOtomasyon.webApi;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restoranOtomasyon.dataAccess.abstracts.DailyProfitRepository;
import com.restoranOtomasyon.entities.concretes.DailyProfit;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class DailyProfitRestController {

	private DailyProfitRepository dailyProfitRepository;
	
	@GetMapping("/daily-profit/latest")
    public ResponseEntity<DailyProfit> getLatestDailyProfit() {
        Optional<DailyProfit> latestDailyProfit = dailyProfitRepository.findTopByOrderByDateDesc();
        if (latestDailyProfit.isPresent()) {
            return ResponseEntity.ok(latestDailyProfit.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
	
	@GetMapping("/daily-profit/last-30-days")
    public ResponseEntity<List<DailyProfit>> getLast30DaysProfit() {
        LocalDate today = LocalDate.now();
        LocalDate thirtyDaysAgo = today.minusDays(30);
        List<DailyProfit> last30DaysProfit = dailyProfitRepository.findByDateBetween(thirtyDaysAgo, today);
        if (!last30DaysProfit.isEmpty()) {
            return ResponseEntity.ok(last30DaysProfit);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
