package com.restoranOtomasyon.webApi;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.restoranOtomasyon.dataAccess.abstracts.DailyProfitRepository;
import com.restoranOtomasyon.dataAccess.abstracts.IncomeAndExpenseRepository;
import com.restoranOtomasyon.dataAccess.abstracts.MonthlyProfitRepository;
import com.restoranOtomasyon.entities.concretes.DailyProfit;
import com.restoranOtomasyon.entities.concretes.IncomeAndExpense;
import com.restoranOtomasyon.entities.concretes.MonthlyProfit;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class IncomeAndExpenseRestController {

	private IncomeAndExpenseRepository ieRepository;

	private DailyProfitRepository dailyProfitRepository;

	private MonthlyProfitRepository monthlyProfitRepository;

	@PostMapping("/createIncomeAndExpense")
	public void add(@RequestParam double income, @RequestParam double expense) {
		IncomeAndExpense ie = new IncomeAndExpense();

		ie.setDate(LocalDate.now());
		ie.setExpense(expense);
		ie.setIncome(income);
		ie.setProfit(income - expense);

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

		YearMonth thisMonth = YearMonth.now();
		List<DailyProfit> dailyProfitsThisMonth = dailyProfitRepository.findByDateBetween(thisMonth.atDay(1),
				thisMonth.atEndOfMonth());
		double totalIncomeThisMonth = dailyProfitsThisMonth.stream().mapToDouble(DailyProfit::getIncome).sum();
		double totalExpenseThisMonth = dailyProfitsThisMonth.stream().mapToDouble(DailyProfit::getExpense).sum();
		double monthlyProfitThisMonth = totalIncomeThisMonth - totalExpenseThisMonth;

		Optional<MonthlyProfit> existingMonthlyProfit = monthlyProfitRepository.findByMonth(thisMonth);
		if (existingMonthlyProfit.isPresent()) {
			MonthlyProfit existing = existingMonthlyProfit.get();
			if (!dailyProfitsThisMonth.isEmpty() && existing.getProfit() != monthlyProfitThisMonth) {
				existing.setIncome(totalIncomeThisMonth);
				existing.setExpense(totalExpenseThisMonth);
				existing.setProfit(monthlyProfitThisMonth);
				monthlyProfitRepository.save(existing);
			}
		} else {
			MonthlyProfit monthlyProfitEntry = new MonthlyProfit();
			monthlyProfitEntry.setMonth(thisMonth);
			monthlyProfitEntry.setExpense(totalExpenseThisMonth);
			monthlyProfitEntry.setIncome(totalIncomeThisMonth);
			monthlyProfitEntry.setProfit(monthlyProfitThisMonth);
			monthlyProfitRepository.save(monthlyProfitEntry);
		}

	}
	
	
	
	
}
