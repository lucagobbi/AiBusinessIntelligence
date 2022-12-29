package com.example.testopenai.repository;

import com.example.testopenai.model.dao.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}
