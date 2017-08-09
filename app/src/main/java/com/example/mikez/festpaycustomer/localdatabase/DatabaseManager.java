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

    public int registerUser(String email, String name, String password, String passwordConfirm) {
        if (email.isEmpty() || name.isEmpty() || password.isEmpty() || passwordConfirm.isEmpty()) {
            return 1;
        }
        if (!password.equals(passwordConfirm)) {
            return 2;
        }
        if (!email.contains("@")){
            return 3;
        }
        if (password.length() <= 4){
            return 4;
        }
        Cursor cursor = getDatabase().getUsers();
        if (cursor.moveToFirst()) {
            do {
                if (email.equals(cursor.getString(DatabaseContract.CURSOR_EMAIL))) {
                    cursor.close();
                    return 5;
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        getDatabase().addUser(email, name, password);
        return 0;
    }

    public int loginUser(String email, String password) {
        if (email.isEmpty() || password.isEmpty()) {
            return 1;
        }
        Cursor cursor = getDatabase().getUsers();
        if (cursor.moveToFirst()) {
            do {
                if (email.equals(cursor.getString(DatabaseContract.CURSOR_EMAIL)) && password.equals(cursor.getString(DatabaseContract.CURSOR_PASSWORD))) {
                    cursor.close();
                    return 0;
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        return 2;
    }

    public DatabaseHelper getDatabase() {
        return database;
    }

    public void setDatabase(DatabaseHelper database) {

        this.database = database;

    }
}
