package com.restoranOtomasyon.dataAccess.abstracts;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restoranOtomasyon.entities.concretes.DailyProfit;

@Repository
public interface DailyProfitRepository extends JpaRepository<DailyProfit, Integer>{

	Optional<DailyProfit> findByDate(LocalDate date);
	List<DailyProfit> findByDateBetween(LocalDate startDate, LocalDate endDate);
	Optional<DailyProfit> findTopByOrderByDateDesc();
	
}
