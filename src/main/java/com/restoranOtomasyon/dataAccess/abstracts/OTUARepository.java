package com.restoranOtomasyon.dataAccess.abstracts;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restoranOtomasyon.entities.concretes.OverTimeUsageAmount;

@Repository
public interface OTUARepository extends JpaRepository<OverTimeUsageAmount, Integer>{

	Optional<OverTimeUsageAmount> findByProductId(int productId);
}
