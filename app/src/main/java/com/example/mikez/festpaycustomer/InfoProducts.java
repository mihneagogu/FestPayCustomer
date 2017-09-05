package com.example.mikez.festpaycustomer;

/**
 * Created by mikez on 7/18/2017.
 */

public class InfoProducts {

    private String name;
    private String vendor;
    private int price;

    public InfoProducts(String name, String vendor, int price) {

        this.name = name;
        this.vendor = vendor;
        this.price = price;

    }

    public String getName() {
        return name;
    }

    public String getVendor() {
        return vendor;
    }

    public int getPrice(){
        return price;
    }

}
