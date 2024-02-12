package com.restoranOtomasyon.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restoranOtomasyon.entities.concretes.KitchenNotification;

public interface KitchenNotificationRepository extends JpaRepository<KitchenNotification, Integer>{

}
