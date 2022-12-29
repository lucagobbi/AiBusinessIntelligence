package com.example.testopenai.repository;

import com.example.testopenai.model.dao.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
