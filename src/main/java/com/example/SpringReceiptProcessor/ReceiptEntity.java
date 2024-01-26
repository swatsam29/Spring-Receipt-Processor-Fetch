package com.example.SpringReceiptProcessor;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "receipt")
public class ReceiptEntity {
    
    @Id
    private String id;

    public String getId() {
        return id;
    } 

}
