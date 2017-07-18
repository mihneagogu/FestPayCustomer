package com.example.mikez.festpaycustomer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editEmail;
    private EditText editName;
    private EditText editPass;
    private EditText editConfirmPass;
    private Button buttonRegister;
    private ImageView imageBack;
    private String email;
    private String name;
    private String pass;
    private String confirmpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        editEmail = (EditText) findViewById(R.id.register_edit_email);
        editName = (EditText) findViewById(R.id.register_edit_name);
        editPass = (EditText) findViewById(R.id.register_edit_pass);
        editConfirmPass = (EditText) findViewById(R.id.register_edit_confirmpass);
        Button buttonRegister = (Button) findViewById(R.id.register_button_register);
        ImageView imageBack = (ImageView) findViewById(R.id.register_button_back);

        editEmail.setOnClickListener(this);
        editName.setOnClickListener(this);
        editPass.setOnClickListener(this);
        editConfirmPass.setOnClickListener(this);
        imageBack.setOnClickListener(this);
        buttonRegister.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.register_button_back:
                Intent intentBack = new Intent(this, LoginActivity.class);
                startActivity(intentBack);
                break;
            case R.id.register_button_register:
                Toast.makeText(this, "Successfully registered", Toast.LENGTH_LONG).show();
                break;

        }
    }
}
