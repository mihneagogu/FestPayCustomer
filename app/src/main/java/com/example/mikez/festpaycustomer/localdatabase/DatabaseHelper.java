package com.example.mikez.festpaycustomer.localdatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import static com.example.mikez.festpaycustomer.localdatabase.DatabaseContract.CURSOR_PRODUCT_ID;


/**
 * Created by mikez on 8/1/2017.
 */

class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "database.db";

    private static final int DATABASE_VERSION = 1;

    private static int maxId;

    private Context context;

    private static final String DATABASE_PRODUCT_QUERY = "CREATE TABLE " + DatabaseContract.ProductsContractEntry.TABLE_NAME + " (" +
            DatabaseContract.ProductsContractEntry.COLUMN_NAME + " TEXT," +
            DatabaseContract.ProductsContractEntry.COLUMN_VENDOR + " TEXT," +
            DatabaseContract.ProductsContractEntry.COLUMN_PRICE + " TEXT," +
            DatabaseContract.ProductsContractEntry.COLUMN_ID + " INT)";

    private static final String DATABASE_HISTORY_QUERY = "CREATE TABLE " + DatabaseContract.HistoryContractEntry.TABLE_NAME + " (" +
            DatabaseContract.HistoryContractEntry.COLUMN_NAME + " TEXT," +
            DatabaseContract.HistoryContractEntry.COLUMN_PRICE + " TEXT," +
            DatabaseContract.HistoryContractEntry.COLUMN_QUANTITY + " TEXT," +
            DatabaseContract.HistoryContractEntry.COLUMN_FINAL_PRICE + " TEXT);";

    DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        setContext(context);

    }

    public void setContext(Context context){
        this.context = context;
    }

    public Context getContext(){
        return context;
    }

    @Override
    public void onCreate(SQLiteDatabase database) {

        database.execSQL(DATABASE_PRODUCT_QUERY);
        database.execSQL(DATABASE_HISTORY_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int i, int il) {
    }

    void addProduct(String name, String vendor, double price, int id) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseContract.ProductsContractEntry.COLUMN_NAME, name);
        contentValues.put(DatabaseContract.ProductsContractEntry.COLUMN_VENDOR, vendor);
        contentValues.put(DatabaseContract.ProductsContractEntry.COLUMN_PRICE, String.valueOf(price));
        contentValues.put(DatabaseContract.ProductsContractEntry.COLUMN_ID, id);
        getWritableDatabase().insert(DatabaseContract.ProductsContractEntry.TABLE_NAME, null, contentValues);
    }

    void addProductToHistory(String name, double price, double quantity, double finalPrice) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseContract.HistoryContractEntry.COLUMN_NAME, name);
        contentValues.put(DatabaseContract.HistoryContractEntry.COLUMN_PRICE, String.valueOf(price));
        contentValues.put(DatabaseContract.HistoryContractEntry.COLUMN_QUANTITY, String.valueOf(quantity));
        contentValues.put(DatabaseContract.HistoryContractEntry.COLUMN_FINAL_PRICE, String.valueOf(finalPrice));
        getWritableDatabase().insert(DatabaseContract.HistoryContractEntry.TABLE_NAME, null, contentValues);
    }


    Cursor getProducts() {
        String[] list = {DatabaseContract.ProductsContractEntry.COLUMN_NAME,
                DatabaseContract.ProductsContractEntry.COLUMN_VENDOR,
                DatabaseContract.ProductsContractEntry.COLUMN_PRICE,
                DatabaseContract.ProductsContractEntry.COLUMN_ID};

        return getReadableDatabase().query(DatabaseContract.ProductsContractEntry.TABLE_NAME, list, null, null, null, null, null);
    }

    Cursor getHistory() {
        String[] list = {DatabaseContract.HistoryContractEntry.COLUMN_NAME,
                DatabaseContract.HistoryContractEntry.COLUMN_PRICE,
                DatabaseContract.HistoryContractEntry.COLUMN_QUANTITY,
                DatabaseContract.HistoryContractEntry.COLUMN_FINAL_PRICE};
        return getReadableDatabase().query(DatabaseContract.HistoryContractEntry.TABLE_NAME, list, null, null, null, null, null);
    }

    public void deleteDatabase() {
        Cursor cursor = getProducts();
        if (cursor.moveToFirst()){
            do {
                String selection = DatabaseContract.ProductsContractEntry.COLUMN_ID + " LIKE ?";
                String[] selectionArgs = {String.valueOf(cursor.getInt(CURSOR_PRODUCT_ID))};
                getWritableDatabase().delete(DatabaseContract.ProductsContractEntry.TABLE_NAME, selection, selectionArgs);
                System.out.println("DELETING ----------------------- \n\n\n\n ");
            } while (cursor.moveToNext());
        }
        cursor.close();
    }

    Cursor getProductForSearch(String productName) {
        String[] results = {DatabaseContract.ProductsContractEntry.COLUMN_NAME,
                DatabaseContract.ProductsContractEntry.COLUMN_VENDOR,
                DatabaseContract.ProductsContractEntry.COLUMN_PRICE,
                DatabaseContract.ProductsContractEntry.COLUMN_ID};

        String selection = DatabaseContract.ProductsContractEntry.COLUMN_NAME + " LIKE ?";
        String[] selectionArgs = {"%" + productName + "%"};
        return getReadableDatabase().query(DatabaseContract.ProductsContractEntry.TABLE_NAME, results, selection, selectionArgs, null, null, null);
    }

    Cursor getOrderedProducts(){
        String[] results = {DatabaseContract.ProductsContractEntry.COLUMN_NAME,
                DatabaseContract.ProductsContractEntry.COLUMN_VENDOR,
                DatabaseContract.ProductsContractEntry.COLUMN_PRICE,
                DatabaseContract.ProductsContractEntry.COLUMN_ID};
        String orderBy = DatabaseContract.ProductsContractEntry.COLUMN_NAME + " asc";
        return getReadableDatabase().query(DatabaseContract.ProductsContractEntry.TABLE_NAME, results, null, null, null, null, orderBy);
    }

}
