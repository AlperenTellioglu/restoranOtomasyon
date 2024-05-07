package com.restoranOtomasyon.dataAccess.abstracts;

import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restoranOtomasyon.entities.concretes.MonthlyProfit;

@Repository
public interface MonthlyProfitRepository extends JpaRepository<MonthlyProfit, Integer>{

	Optional<MonthlyProfit> findByMonth(YearMonth month);
	List<MonthlyProfit> findAllByOrderByMonthDesc();
	List<MonthlyProfit> findTop12ByOrderByMonthDesc();
	List<MonthlyProfit> findByMonthBetween(YearMonth startDate, YearMonth endDate);
}
