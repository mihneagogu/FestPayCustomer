package com.example.mikez.festpaycustomer;

import android.app.Activity;
import android.content.Intent;
import android.net.Network;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mikez.festpaycustomer.fragments.FirstActivity;
import com.example.mikez.festpaycustomer.localdatabase.DatabaseManager;
import com.example.mikez.festpaycustomer.network.NetworkManager;
import com.example.mikez.festpaycustomer.network.ProductModel;
import com.example.mikez.festpaycustomer.network.ProductResponse;
import com.example.mikez.festpaycustomer.network.UserResponse;

import java.util.List;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener, UserResponse {

    private EditText editEmail;
    private EditText editConfirmEmail;
    private EditText editPass;
    private EditText editConfirmPass;
    private Button buttonRegister;
    private ImageView imageBack;
    private String email;
    private String name;
    private String pass;
    private String confirmpass;
    private DatabaseManager database;
    private int registerCase;
    private NetworkManager network;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        editEmail = (EditText) findViewById(R.id.register_edit_email);
        editConfirmEmail = (EditText) findViewById(R.id.register_edit_confirm_email);
        editPass = (EditText) findViewById(R.id.register_edit_pass);
        editConfirmPass = (EditText) findViewById(R.id.register_edit_confirmpass);
        Button buttonRegister = (Button) findViewById(R.id.register_button_register);
        ImageView imageBack = (ImageView) findViewById(R.id.register_button_back);

        network = new NetworkManager(this, NetworkManager.KEY_USER);
        database = new DatabaseManager(this);

        imageBack.setOnClickListener(this);
        buttonRegister.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.register_button_back:
                Intent intentBack = new Intent(this, LoginActivity.class);
                startActivity(intentBack);
                finish();
                break;
            case R.id.register_button_register:
                network.register(editEmail.getText().toString(), editConfirmEmail.getText().toString(), editPass.getText().toString(), editConfirmPass.getText().toString());
                break;

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intentBack = new Intent(this, LoginActivity.class);
        startActivity(intentBack);
        finish();

    }

    @Override
    public void logIn() {

    }

    @Override
    public void passwordForgotten() {

    }

    @Override
    public void register() {
        Intent intentMain = new Intent(this, FirstActivity.class);
        startActivity(intentMain);
        finish();

        Toast.makeText(this, "Registration successful.", Toast.LENGTH_SHORT).show();
    }

}
