package com.example.mikez.festpaycustomer.localdatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



/**
 * Created by mikez on 8/1/2017.
 */

 class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "database.db";

    private static final int DATABSE_VERSION = 1;


    private static final String DATABASE_PRODUCT_QUERY = "CREATE TABLE " + DatabaseContract.ProductsContractEntry.TABLE_NAME + " (" +
            DatabaseContract.ProductsContractEntry.COLUMN_NAME + " TEXT," +
            DatabaseContract.ProductsContractEntry.COLUMN_VENDOR + " TEXT," +
            DatabaseContract.ProductsContractEntry.COLUMN_PRICE + " TEXT);";
    DatabaseHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABSE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase database) {

        database.execSQL(DATABASE_PRODUCT_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int i, int il) {
    }

    void addProduct (String name, String vendor, double price){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseContract.ProductsContractEntry.COLUMN_NAME, name);
        contentValues.put(DatabaseContract.ProductsContractEntry.COLUMN_VENDOR, vendor);
        contentValues.put(DatabaseContract.ProductsContractEntry.COLUMN_PRICE, String.valueOf(price));
        getWritableDatabase().insert(DatabaseContract.ProductsContractEntry.TABLE_NAME, null, contentValues);
    }


    Cursor getProducts(){
        String[] list = {DatabaseContract.ProductsContractEntry.COLUMN_NAME,
        DatabaseContract.ProductsContractEntry.COLUMN_VENDOR,
        DatabaseContract.ProductsContractEntry.COLUMN_PRICE};

        return getReadableDatabase().query(DatabaseContract.ProductsContractEntry.TABLE_NAME, list, null, null, null, null, null);
    }


    Cursor getProductForSearch(String productName) {
        String[] results = {DatabaseContract.ProductsContractEntry.COLUMN_NAME,
        DatabaseContract.ProductsContractEntry.COLUMN_VENDOR,
        DatabaseContract.ProductsContractEntry.COLUMN_PRICE};

        String selection = DatabaseContract.ProductsContractEntry.COLUMN_NAME + " LIKE ?";
        String[] selectionArgs = {"%" + productName + "%"};
        return getReadableDatabase().query(DatabaseContract.ProductsContractEntry.TABLE_NAME, results, selection, selectionArgs, null, null, null);
    }
}
