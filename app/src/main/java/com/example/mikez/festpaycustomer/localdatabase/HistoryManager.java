package com.example.mikez.festpaycustomer.localdatabase;

import android.content.Context;
import android.database.Cursor;


import com.example.mikez.festpaycustomer.network.HistoryModel;
import com.example.mikez.festpaycustomer.network.ProductModel;

import java.util.ArrayList;
import java.util.List;

import static com.example.mikez.festpaycustomer.localdatabase.DatabaseContract.CURSOR_HISTORY_FINAL_PRICE;
import static com.example.mikez.festpaycustomer.localdatabase.DatabaseContract.CURSOR_HISTORY_NAME;
import static com.example.mikez.festpaycustomer.localdatabase.DatabaseContract.CURSOR_HISTORY_PRICE;
import static com.example.mikez.festpaycustomer.localdatabase.DatabaseContract.CURSOR_HISTORY_QUANTITY;
import static com.example.mikez.festpaycustomer.localdatabase.DatabaseContract.CURSOR_PRODUCT_NAME;
import static com.example.mikez.festpaycustomer.localdatabase.DatabaseContract.CURSOR_PRODUCT_PRICE;

import com.example.mikez.festpaycustomer.util.Preference;

import static com.example.mikez.festpaycustomer.localdatabase.DatabaseContract.CURSOR_PRODUCT_VENDOR;

/**
 * Created by mikez on 9/19/2017.
 */

public class HistoryManager {

    private Context context;
    private DatabaseHelper database;
    private Preference preference;
    private List<HistoryModel> products;
    private List<HistoryModel> history;
    private static final String KEY_ADDED_HISTORY = "added";

    public HistoryManager(Context context) {
        products = new ArrayList<>();
        this.context = context;
        setDatabase(new DatabaseHelper(context));
        preference = new Preference(context);


    }



    public void addHistory(List<HistoryModel> products) {
        boolean added = preference.getPreferenceBoolean(KEY_ADDED_HISTORY);
        if (!added) {
            for (HistoryModel x : products) {
                database.addProductToHistory(x.getName(), x.getPrice(), x.getQuantity(), x.getFinalPrice());
                preference.setPreference(KEY_ADDED_HISTORY, true);
            }
        }

    }

    public List<HistoryModel> getHistory() {
        List<HistoryModel> result = new ArrayList<>();
        Cursor cursor = database.getHistory();
        if (cursor.moveToFirst()) {
            do {
                result.add(new HistoryModel(cursor.getString(CURSOR_HISTORY_NAME), Double.parseDouble(cursor.getString(CURSOR_HISTORY_PRICE)),
                        Double.parseDouble(cursor.getString(CURSOR_HISTORY_QUANTITY)), Double.parseDouble(cursor.getString(CURSOR_HISTORY_FINAL_PRICE))));
            } while (cursor.moveToNext());
        }
        return result;
    }

    private void setDatabase(DatabaseHelper database) {
        this.database = database;
    }
}
