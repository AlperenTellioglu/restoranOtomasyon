package com.restoranOtomasyon.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restoranOtomasyon.entities.concretes.CashRegister;

public interface CashRegisterRepository extends JpaRepository<CashRegister, Integer>{

}
