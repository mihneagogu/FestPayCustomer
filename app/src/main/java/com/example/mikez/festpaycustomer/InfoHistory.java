package com.example.mikez.festpaycustomer;

/**
 * Created by mikez on 7/18/2017.
 */

public class InfoHistory {

    private int productId;
    private String name;
    private String price;
    private String quantity;
    private String finalPrice;

    public InfoHistory(int productId, String name, String price, String quantity, String finalPrice) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.finalPrice = finalPrice;
    }

    public int getProductId() {
        return productId;

    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getFinalPrice() {
        return finalPrice;
    }
}
