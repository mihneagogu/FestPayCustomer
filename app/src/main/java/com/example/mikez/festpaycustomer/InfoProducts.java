package com.example.mikez.festpaycustomer;

/**
 * Created by mikez on 7/18/2017.
 */

public class InfoProducts {

    private int productId;
    private String name;
    private String vendor;
    private String price;

    public InfoProducts(int productId, String name, String vendor, String price) {

        this.productId = productId;
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

    public String getPrice(){
        return price;
    }

}
