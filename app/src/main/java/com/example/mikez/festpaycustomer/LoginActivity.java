package com.example.mikez.festpaycustomer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mikez.festpaycustomer.localdatabase.DatabaseManager;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editEmail;
    private EditText editPassword;
    private DatabaseManager database;
    private int loginCase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editEmail = (EditText) findViewById(R.id.edit_login_email);
        editPassword = (EditText) findViewById(R.id.edit_login_password);
        TextView textForgotPass = (TextView) findViewById(R.id.text_login_forgotpass);
        Button buttonLogin = (Button) findViewById(R.id.button_login_login);
        Button buttonRegister = (Button) findViewById(R.id.button_login_register);
        ImageView imageFacebook = (ImageView) findViewById(R.id.login_image_facebook);
        ImageView imageGoogle = (ImageView) findViewById(R.id.login_image_google);

        database = new DatabaseManager(this);

        buttonLogin.setOnClickListener(this);
        buttonRegister.setOnClickListener(this);
        textForgotPass.setOnClickListener(this);
        imageFacebook.setOnClickListener(this);
        imageGoogle.setOnClickListener(this);

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_login_login:
                loginCase = database.loginUser(editEmail.getText().toString(), editPassword.getText().toString());
                if (loginCase == 0) {
                    Toast.makeText(this, "Login successful.", Toast.LENGTH_SHORT).show();
                    Intent intentLogin = new Intent(this, MainActivity.class);
                    startActivity(intentLogin);
                    finish();
                } else if (loginCase == 1) {
                    Toast.makeText(this, "E-mail or password is empty", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "E-mail and password do not match", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.button_login_register:
                Intent intentRegister = new Intent(this, RegisterActivity.class);
                startActivity(intentRegister);
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

}
