package com.restoranOtomasyon.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restoranOtomasyon.entities.concretes.Menu;

public interface MenuRepository extends JpaRepository<Menu, Integer>{

}
