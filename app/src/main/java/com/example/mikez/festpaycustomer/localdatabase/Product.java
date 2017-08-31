package com.example.mikez.festpaycustomer.localdatabase;

/**
 * Created by mikez on 8/31/2017.
 */

public class Product {

    private String name;
    private String vendor;
    private int price;

    public Product(String name, String vendor, int price){
        this.name = name;
        this.vendor = vendor;
        this.price = price;
    }

    public String getName(){
        return name;
    }
    public String getVendor(){
        return vendor;
    }
    public int getPrice(){
        return price;
    }
}
