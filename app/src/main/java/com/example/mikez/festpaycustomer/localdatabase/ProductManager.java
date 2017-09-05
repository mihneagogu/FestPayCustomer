package com.example.mikez.festpaycustomer.localdatabase;

import android.content.Context;
import android.database.Cursor;

import com.example.mikez.festpaycustomer.InfoProducts;

import java.util.ArrayList;
import java.util.List;


import static com.example.mikez.festpaycustomer.localdatabase.DatabaseContract.CURSOR_PRODUCT_PRICE;

/**
 * Created by mikez on 8/31/2017.
 */

public class ProductManager {


    private DatabaseHelper database;
    private List<String> optionsSearch = new ArrayList<>();
    public ProductManager(Context context) {
        setDatabase(new DatabaseHelper(context));
    }


    public List<InfoProducts> registerProduct(String name, String vendor, int price) {
        List<InfoProducts> products = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            if (i==0) {
                name += String.valueOf(i);
                vendor += String.valueOf(i);
            } else {
                name = name.substring(0,name.length()-1) + String.valueOf(i);
                vendor = vendor.substring(0, vendor.length()-1) + String.valueOf(i);
            }
            price += i * 100;
            getDatabase().addProduct(name, vendor, price);
            products.add(new InfoProducts(name, vendor, price));
            optionsSearch.add(name);
        }
        return products;

    }

    public List<String> getSearchOptions(){
        return optionsSearch;
    }

    public List<Product> searchProduct(String productName) {
        Cursor cursor = getDatabase().getProductForSearch(productName);
        List<Product> products = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                products.add(new Product(cursor.getString(DatabaseContract.CURSOR_PRODUCT_NAME), cursor.getString(DatabaseContract.CURSOR_PRODUCT_VENDOR), cursor.getInt(CURSOR_PRODUCT_PRICE)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return products;
    }


    public void setDatabase(DatabaseHelper database) {

        this.database = database;

    }

    public DatabaseHelper getDatabase() {
        return database;
    }
}

