package com.example.testopenai.repository;

import com.example.testopenai.model.dao.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
