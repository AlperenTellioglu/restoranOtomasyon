package com.restoranOtomasyon.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restoranOtomasyon.entities.concretes.WaiterNotification;

@Repository
public interface WaiterNotificationRepository extends JpaRepository<WaiterNotification, Integer>{

}
