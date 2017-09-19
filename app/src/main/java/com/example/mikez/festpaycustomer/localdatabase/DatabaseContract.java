package com.example.mikez.festpaycustomer.localdatabase;

import android.provider.BaseColumns;

/**
 * Created by mikez on 8/1/2017.
 */

 class DatabaseContract {


    static final int CURSOR_PRODUCT_NAME = 0;
    static final int CURSOR_PRODUCT_VENDOR = 1;
    static final int CURSOR_PRODUCT_PRICE = 2;
    static final int CURSOR_PRODUCT_ID = 3;


    static final int CURSOR_HISTORY_NAME = 0;
    static final int CURSOR_HISTORY_PRICE = 1;
    static final int CURSOR_HISTORY_QUANTITY = 2;
    static final int CURSOR_HISTORY_FINAL_PRICE = 3;




    abstract class ProductsContractEntry implements BaseColumns {
        static final String TABLE_NAME = "Product";
        static final String COLUMN_NAME = "Name";
        static final String COLUMN_VENDOR = "Vendor";
        static final String COLUMN_PRICE = "Price";
        static final String COLUMN_ID = "Id";
    }

    abstract class HistoryContractEntry implements BaseColumns {
        static final String TABLE_NAME = "History";
        static final String COLUMN_NAME = "Name";
        static final String COLUMN_PRICE = "Price";
        static final String COLUMN_QUANTITY = "Quantity";
        static final String COLUMN_FINAL_PRICE = "finalPrice";

    }

}
