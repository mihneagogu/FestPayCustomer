package com.example.mikez.festpaycustomer.localdatabase;

import android.content.Context;
import android.database.Cursor;
import android.widget.Toast;

/**
 * Created by mikez on 8/1/2017.
 */

public class DatabaseManager {
    private DatabaseHelper database;

    public DatabaseManager(Context context) {
        setDatabase(new DatabaseHelper(context));
    }




    public DatabaseHelper getDatabase() {
        return database;
    }

    public void setDatabase(DatabaseHelper database) {

        this.database = database;

    }
}
