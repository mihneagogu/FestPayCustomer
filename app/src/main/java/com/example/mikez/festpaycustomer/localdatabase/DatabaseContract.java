package com.example.mikez.festpaycustomer.localdatabase;

import android.provider.BaseColumns;

/**
 * Created by mikez on 8/1/2017.
 */

 class DatabaseContract {

    static final int CURSOR_ID = 0;
    static final int CURSOR_EMAIL = 1;
    static final int CURSOR_NAME = 2;
    static final int CURSOR_PASSWORD = 3;

    static final int CURSOR_PRODUCT_NAME = 0;
    static final int CURSOR_PRODUCT_VENDOR = 1;
    static final int CURSOR_PRODUCT_PRICE = 2;


    abstract class UserContractEntry implements BaseColumns {
        static final String TABLE_NAME = "User";
        static final String COLUMN_ID = "Id";
        static final String COLUMN_EMAIL = "Email";
        static final String COLUMN_NAME = "Name";
        static final String COLUMN_PASSWORD = "Password";

    }

    abstract class ProductsContractEntry implements BaseColumns {
        static final String TABLE_NAME = "Product";
        static final String COLUMN_NAME = "Name";
        static final String COLUMN_VENDOR = "Vendor";
        static final String COLUMN_PRICE = "Price";
    }
}
