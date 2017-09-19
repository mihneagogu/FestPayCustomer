package com.example.mikez.festpaycustomer.network;

import java.util.List;

/**
 * Created by mikez on 9/11/2017.
 */

public class ProductModel {

    private int id;
    private String name;
    private double price;
    private String description;
    private String category;
    private String shop;
    private String imageURL;

    public ProductModel(int id, String name, String imageURL, double price, String description, String category, String shop) {
        setId(id);
        setName(name);
        setImageURL(imageURL);
        setPrice(price);
        setDescription(description);
        setCategory(category);
        setShop(shop);
    }

    public ProductModel(String name, String shop, double price, int id){
        setName(name);
        setShop(shop);
        setPrice(price);
        setId(id);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public String getShop() {
        return shop;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
