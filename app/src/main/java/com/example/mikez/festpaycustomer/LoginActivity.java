package com.example.mikez.festpaycustomer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mikez.festpaycustomer.localdatabase.DatabaseManager;
import com.example.mikez.festpaycustomer.network.NetworkManager;
import com.example.mikez.festpaycustomer.network.UserResponse;
import com.example.mikez.festpaycustomer.util.Preference;

import static com.example.mikez.festpaycustomer.util.Preference.KEY_REMEBER;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, UserResponse {

    private EditText editEmail;
    public CheckBox checkRememberMe;
    private EditText editPassword;
    private DatabaseManager database;
    private int loginCase;
    private NetworkManager network;
    private String nullString = "";
    private Preference preference;
    private static final String PREF_NAME = "prefs";
    private static final String KEY_REMEMBER = "remember";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        preference = new Preference(this);

        editEmail = (EditText) findViewById(R.id.edit_login_email);
        editPassword = (EditText) findViewById(R.id.edit_login_password);
        TextView textForgotPass = (TextView) findViewById(R.id.text_login_forgotpass);
        Button buttonLogin = (Button) findViewById(R.id.button_login_login);
        Button buttonRegister = (Button) findViewById(R.id.button_login_register);
        ImageView imageFacebook = (ImageView) findViewById(R.id.login_image_facebook);
        ImageView imageGoogle = (ImageView) findViewById(R.id.login_image_google);
        checkRememberMe = (CheckBox) findViewById(R.id.login_check_box_remember_me);

        network = new NetworkManager(this, NetworkManager.KEY_USER);
        database = new DatabaseManager(this);

        if (preference.getPreferenceBoolean(KEY_REMEMBER)) {
            network.logIn(preference.getPreferenceString(KEY_EMAIL), preference.getPreferenceString(KEY_PASSWORD));
        }


        buttonLogin.setOnClickListener(this);
        buttonRegister.setOnClickListener(this);
        textForgotPass.setOnClickListener(this);
        imageFacebook.setOnClickListener(this);
        imageGoogle.setOnClickListener(this);

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_login_login:
                network.logIn(editEmail.getText().toString(), editPassword.getText().toString());
                if (checkRememberMe.isChecked()) {
                    preference.setPreference(KEY_REMEBER, true);
                    preference.setPreference(KEY_EMAIL, editEmail.getText().toString());
                    preference.setPreference(KEY_PASSWORD, editPassword.getText().toString());
                } else {
                    preference.setPreference(KEY_REMEBER, false);
                }
                break;
            case R.id.button_login_register:
                Intent intentRegister = new Intent(this, RegisterActivity.class);
                startActivity(intentRegister);
                finish();
                break;
            case R.id.text_login_forgotpass:
                Toast.makeText(this, "Soon!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.login_image_facebook:
                Toast.makeText(this, "Soon Facebook Log-in!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.login_image_google:
                Toast.makeText(this, "Soon Google Log-in!", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void logIn() {
        Intent intentLogin = new Intent(this, MainActivity.class);
        startActivity(intentLogin);
        finish();
    }

    @Override
    public void passwordForgotten() {

    }

    @Override
    public void register() {

    }


}
