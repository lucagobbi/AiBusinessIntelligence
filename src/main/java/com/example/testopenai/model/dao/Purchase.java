package com.example.testopenai.model.dao;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "purchase")
    private List<ItemPurchase> itemPurchases;

    private Date creationDate;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

}
