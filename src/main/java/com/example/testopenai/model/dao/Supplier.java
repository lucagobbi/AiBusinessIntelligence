package com.example.testopenai.model.dao;

import com.example.testopenai.model.dao.Category;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String companyName;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

}
