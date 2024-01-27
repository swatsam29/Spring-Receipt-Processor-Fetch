package com.example.SpringReceiptProcessor.Controller;

import lombok.Data;

@Data
public  class GenerateIdResponse {
    private String id;

    public GenerateIdResponse(String id) {
        this.id = id;
    }

    
}

