package com.example.testopenai.model.dao;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Embeddable
public class ItemPurchaseId implements Serializable {

    private static final long serialVersionUID = 0L;

    @Column(name = "item_id")
    private long itemId;

    @Column(name = "purchase_id")
    private long purchaseId;


}