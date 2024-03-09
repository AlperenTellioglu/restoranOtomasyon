package com.restoranOtomasyon.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restoranOtomasyon.entities.concretes.IncomeAndExpense;

@Repository
public interface IncomeAndExpenseRepository extends JpaRepository<IncomeAndExpense, Integer>{

}
