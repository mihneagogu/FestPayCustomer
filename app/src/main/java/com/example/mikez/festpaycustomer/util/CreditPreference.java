package com.example.mikez.festpaycustomer.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by mikez on 9/19/2017.
 */

public class CreditPreference {

    public static final String KEY_CREDITS = "credits";
    private static final String KEY_INITIALIZE = "initialize";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public CreditPreference(Context context) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(KEY_INITIALIZE, Context.MODE_PRIVATE);
            editor = sharedPreferences.edit();
        }
    }

    private void savePreference(){
        editor.commit();
    }

    public void setPreference(String key, String value){
       editor.putString(key, value);
        savePreference();
    }

    public String getStringPreference(String key){
        return sharedPreferences.getString(key, null);
    }

}
