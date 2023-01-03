package com.example.testopenai.model.dao;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ItemPurchase {

    @EmbeddedId
    ItemPurchaseId id;

}
