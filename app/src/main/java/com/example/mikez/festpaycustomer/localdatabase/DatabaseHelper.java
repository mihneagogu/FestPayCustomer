package com.example.mikez.festpaycustomer.localdatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.mikez.festpaycustomer.localdatabase.DatabaseContract.CURSOR_ID;

/**
 * Created by mikez on 8/1/2017.
 */

 class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "database.db";

    private static final int DATABSE_VERSION = 1;

    private static final String DATABASE_USER_QUERY = "CREATE TABLE " + DatabaseContract.UserContractEntry.TABLE_NAME + " (" +
            DatabaseContract.UserContractEntry.COLUMN_ID + " NUMBER," +
            DatabaseContract.UserContractEntry.COLUMN_EMAIL + " TEXT," +
            DatabaseContract.UserContractEntry.COLUMN_NAME + " TEXT," +
            DatabaseContract.UserContractEntry.COLUMN_PASSWORD + " TEXT);";

    private static final String DATABASE_PRODUCT_QUERY = "CREATE TABLE " + DatabaseContract.ProductsContractEntry.TABLE_NAME + " (" +
            DatabaseContract.ProductsContractEntry.COLUMN_NAME + " TEXT," +
            DatabaseContract.ProductsContractEntry.COLUMN_VENDOR + " TEXT," +
            DatabaseContract.ProductsContractEntry.COLUMN_PRICE + " NUMBER);";
    DatabaseHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABSE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase database) {

        database.execSQL(DATABASE_USER_QUERY);
        database.execSQL(DATABASE_PRODUCT_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int i, int il) {
    }

    void addUser(String email, String name, String password){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseContract.UserContractEntry.COLUMN_ID, getGenerateId());
        contentValues.put(DatabaseContract.UserContractEntry.COLUMN_EMAIL, email);
        contentValues.put(DatabaseContract.UserContractEntry.COLUMN_NAME, name);
        contentValues.put(DatabaseContract.UserContractEntry.COLUMN_PASSWORD, password);
        getWritableDatabase().insert(DatabaseContract.UserContractEntry.TABLE_NAME, null, contentValues);
    }

    void addProduct (String name, String vendor, int price){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseContract.ProductsContractEntry.COLUMN_NAME, name);
        contentValues.put(DatabaseContract.ProductsContractEntry.COLUMN_VENDOR, vendor);
        contentValues.put(DatabaseContract.ProductsContractEntry.COLUMN_PRICE, price);
        getWritableDatabase().insert(DatabaseContract.ProductsContractEntry.TABLE_NAME, null, contentValues);
    }

    Cursor getUsers(){
        String[] list = {DatabaseContract.UserContractEntry.COLUMN_ID,
                DatabaseContract.UserContractEntry.COLUMN_EMAIL,
                DatabaseContract.UserContractEntry.COLUMN_NAME,
                DatabaseContract.UserContractEntry.COLUMN_PASSWORD};
        return getReadableDatabase().query(DatabaseContract.UserContractEntry.TABLE_NAME, list, null, null, null, null, null);

    }

    Cursor getProducts(){
        String[] list = {DatabaseContract.ProductsContractEntry.COLUMN_NAME,
        DatabaseContract.ProductsContractEntry.COLUMN_VENDOR,
        DatabaseContract.ProductsContractEntry.COLUMN_PRICE};

        return getReadableDatabase().query(DatabaseContract.ProductsContractEntry.TABLE_NAME, list, null, null, null, null, null);
    }

    private int getGenerateId(){

        int id = 0;
        Cursor cursor = getUsers();

        if (cursor.moveToLast()) {
            id = cursor.getInt(CURSOR_ID);
        }
        cursor.close();
        return ++id;
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
