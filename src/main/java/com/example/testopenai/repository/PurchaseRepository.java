package com.example.testopenai.repository;

import com.example.testopenai.model.dao.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
}
