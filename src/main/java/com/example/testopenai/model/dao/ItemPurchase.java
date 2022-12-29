package com.example.testopenai.model.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ItemPurchase {

    @JsonIgnore
    @EmbeddedId
    ItemPurchaseId id;

    @ManyToOne
    @JoinColumn(name = "item_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Item item;

    @ManyToOne
    @JoinColumn(name = "purchase_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Purchase purchase;

}
