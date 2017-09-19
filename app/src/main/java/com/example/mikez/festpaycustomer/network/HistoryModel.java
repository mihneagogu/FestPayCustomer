package com.example.mikez.festpaycustomer.network;

import com.example.mikez.festpaycustomer.adapters.HistoryAdapter;

/**
 * Created by mikez on 9/19/2017.
 */

public class HistoryModel {

    private String name;
    private double price;
    private double quantity;
    private double finalPrice;

    public HistoryModel(String name, double price, double quantity, double finalPrice){
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.finalPrice = finalPrice;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }



    public double getQuantity() {
        return quantity;
    }


    public double getFinalPrice() {
        return finalPrice;
    }
}
