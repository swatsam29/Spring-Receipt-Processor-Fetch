package com.example.SpringReceiptProcessor.Model;

import java.math.BigDecimal;

public class ItemsData {
     String shortDescription;
    BigDecimal price;

    public String toString() {
        return "ReceiptItem [shortDescription=" + shortDescription + ", price=" + price + "]";
    }
}
