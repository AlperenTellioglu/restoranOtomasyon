package com.restoranOtomasyon.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restoranOtomasyon.entities.concretes.WaiterNotification;

public interface WaiterNotificationRepository extends JpaRepository<WaiterNotification, Integer>{

}
