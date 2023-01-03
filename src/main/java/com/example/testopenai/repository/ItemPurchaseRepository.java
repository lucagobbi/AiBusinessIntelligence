package com.example.testopenai.repository;

import com.example.testopenai.model.dao.ItemPurchase;
import com.example.testopenai.model.dao.ItemPurchaseId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemPurchaseRepository extends JpaRepository<ItemPurchase, ItemPurchaseId> {
}
