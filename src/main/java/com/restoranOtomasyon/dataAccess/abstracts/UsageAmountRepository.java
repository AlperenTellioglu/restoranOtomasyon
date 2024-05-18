package com.restoranOtomasyon.dataAccess.abstracts;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restoranOtomasyon.entities.concretes.UsageAmount;

@Repository
public interface UsageAmountRepository extends JpaRepository<UsageAmount, Integer>{

	Optional<UsageAmount> findByProductId(int productId);
}
