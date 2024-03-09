package com.restoranOtomasyon.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restoranOtomasyon.entities.concretes.CustomerTable;

@Repository
public interface CustomerTableRepository extends JpaRepository<CustomerTable, Integer>{

	List<CustomerTable> findByStatus(boolean status);

}
