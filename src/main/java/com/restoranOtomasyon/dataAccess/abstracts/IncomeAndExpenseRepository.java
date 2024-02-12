package com.restoranOtomasyon.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restoranOtomasyon.entities.concretes.IncomeAndExpense;

public interface IncomeAndExpenseRepository extends JpaRepository<IncomeAndExpense, Integer>{

}
