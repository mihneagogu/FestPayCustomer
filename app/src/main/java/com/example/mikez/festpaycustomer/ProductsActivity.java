package com.example.mikez.festpaycustomer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ProductsActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView imageBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        imageBack = (ImageView) findViewById(R.id.products_button_back);

        imageBack.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.products_button_back:
                finish();
                break;
        }
    }
}
