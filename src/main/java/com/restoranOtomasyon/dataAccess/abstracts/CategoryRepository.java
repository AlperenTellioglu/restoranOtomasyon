package com.restoranOtomasyon.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restoranOtomasyon.entities.concretes.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{

}
