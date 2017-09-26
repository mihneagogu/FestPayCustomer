package com.example.mikez.festpaycustomer.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by AoD Akitektuo on 11-Jul-17 at 21:29.
 */

public class Preference {

    public static final String KEY_REMEBER = "remember";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_NFC_DATA = "nfc_data";
    private static final String KEY_INITIALIZE = "initialize";
    private static final String KEY_ADDED_HISTORY = "added";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public Preference(Context context) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(KEY_INITIALIZE, Context.MODE_PRIVATE);
            editor = sharedPreferences.edit();
        }
    }

    private void savePreference() {
        editor.commit();
    }

    public void setPreference(String key, boolean bool) {
        editor.putBoolean(key, bool);
        savePreference();
    }

    public void setPreference(String key, String str) {
        editor.putString(key, str);
        savePreference();
    }

    public boolean getPreferenceBoolean(String key) {
        return sharedPreferences.getBoolean(key, false);
    }

    public String getPreferenceString(String key) {
        return sharedPreferences.getString(key, null);
    }

}
