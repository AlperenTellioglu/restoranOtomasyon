package com.restoranOtomasyon.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restoranOtomasyon.entities.concretes.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{

}
