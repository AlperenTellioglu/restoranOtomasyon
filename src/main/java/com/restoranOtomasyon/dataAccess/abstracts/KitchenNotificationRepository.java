package com.restoranOtomasyon.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restoranOtomasyon.entities.concretes.KitchenNotification;

@Repository
public interface KitchenNotificationRepository extends JpaRepository<KitchenNotification, Integer>{

}
