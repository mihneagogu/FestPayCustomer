package com.example.mikez.festpaycustomer.localdatabase;

import android.content.Context;
import android.database.Cursor;

/**
 * Created by mikez on 8/1/2017.
 */

public class DatabaseManager {
    private DatabaseHelper database;

    public DatabaseManager(Context context) {
        setDatabase(new DatabaseHelper(context));
    }

    public boolean registerUser(String email, String name, String password, String passwordConfirm) {
        if (email.isEmpty() || name.isEmpty() || password.isEmpty() || passwordConfirm.isEmpty()) {
            return false;
        }
        if (!password.equals(passwordConfirm)) {
            return false;
        }
        if (!email.contains("@") || password.length() < 4) {
            return false;
        }
        Cursor cursor = getDatabase().getUsers();
        if (cursor.moveToFirst()) {
            do {
                if (email.equals(cursor.getString(DatabaseContract.CURSOR_EMAIL))) {
                    cursor.close();
                    return false;
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        getDatabase().addUser(email, name, password);
        return true;
    }

    public boolean loginUser(String email, String password) {
        if (email.isEmpty() || password.isEmpty()) {
            return false;
        }
        Cursor cursor = getDatabase().getUsers();
        if (cursor.moveToFirst()) {
            do {
                if (email.equals(cursor.getString(DatabaseContract.CURSOR_EMAIL)) && password.equals(cursor.getString(DatabaseContract.CURSOR_PASSWORD))) {
                    cursor.close();
                    return true;
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        return false;
    }

    public DatabaseHelper getDatabase() {
        return database;
    }

    public void setDatabase(DatabaseHelper database) {

        this.database = database;

    }
}
