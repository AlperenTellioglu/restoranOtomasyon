package com.restoranOtomasyon.webApi;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restoranOtomasyon.business.requests.CreateIncomeAndExpenseRequest;
import com.restoranOtomasyon.dataAccess.abstracts.DailyProfitRepository;
import com.restoranOtomasyon.dataAccess.abstracts.IncomeAndExpenseRepository;
import com.restoranOtomasyon.entities.concretes.DailyProfit;
import com.restoranOtomasyon.entities.concretes.IncomeAndExpense;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class IncomeAndExpenseRestController {

	private IncomeAndExpenseRepository ieRepository;

	private DailyProfitRepository dailyProfitRepository;

	@PostMapping("/createIncomeAndExpense")
	public void add(CreateIncomeAndExpenseRequest request) {
		IncomeAndExpense ie = new IncomeAndExpense();

		ie.setDate(LocalDate.now());
		ie.setExpense(request.getExpense());
		ie.setIncome(request.getIncome());
		ie.setProfit(request.getIncome() - request.getExpense());

		this.ieRepository.save(ie);

		LocalDate today = LocalDate.now();
		DailyProfit dailyProfitEntry = new DailyProfit();
		dailyProfitEntry.setDate(today);
		dailyProfitEntry.setIncome(ie.getIncome());
		dailyProfitEntry.setExpense(ie.getExpense());
		dailyProfitEntry.setProfit(dailyProfitEntry.getIncome() - dailyProfitEntry.getExpense());

		Optional<DailyProfit> existingDailyProfit = dailyProfitRepository.findByDate(today);
		if (existingDailyProfit.isPresent()) {
			DailyProfit existing = existingDailyProfit.get();
			existing.setIncome(existing.getIncome() + dailyProfitEntry.getIncome());
			existing.setExpense(existing.getExpense() + dailyProfitEntry.getExpense());
			existing.setProfit(existing.getProfit() + dailyProfitEntry.getProfit());
			dailyProfitRepository.save(existing);

		} else {
			dailyProfitRepository.save(dailyProfitEntry);
		}

	}
}
