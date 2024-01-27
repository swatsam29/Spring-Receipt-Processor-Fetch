package com.example.SpringReceiptProcessor.Model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class ReceiptsData {

    private String id;
    private int points;
    private String retailer;
    private BigDecimal total;
    private LocalDateTime purchaseDateTime;
    private List<Item> items;

    public String getId() {
        return id;
    }

    public int getPoints() {
        return points;
    }

    public String getRetailer() {
        return retailer;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public LocalDateTime getPurchaseDateTime() {
        return purchaseDateTime;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setRetailer(String retailer) {
        this.retailer = retailer;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public void setPurchaseDateTime(LocalDateTime purchaseDateTime) {
        this.purchaseDateTime = purchaseDateTime;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public static class Item {
        private String shortDescription;
        private BigDecimal price;  // Change the type to BigDecimal

        public String getShortDescription() {
            return shortDescription;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public void setShortDescription(String shortDescription) {
            this.shortDescription = shortDescription;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }
    }

    @Override
    public String toString() {
        return "ReceiptsData [id=" + id + ", points=" + points + ", retailer=" + retailer +
                ", purchaseDateTime=" + purchaseDateTime + ", total=" + total + ", items=" + items + "]";
    }
}
