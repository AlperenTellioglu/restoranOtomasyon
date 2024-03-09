package com.restoranOtomasyon.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restoranOtomasyon.entities.concretes.CashRegister;

@Repository
public interface CashRegisterRepository extends JpaRepository<CashRegister, Integer>{

}
