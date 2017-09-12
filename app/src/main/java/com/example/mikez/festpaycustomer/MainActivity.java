package com.example.mikez.festpaycustomer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mikez.festpaycustomer.localdatabase.ProductManager;
import com.example.mikez.festpaycustomer.network.ProductModel;
import com.example.mikez.festpaycustomer.network.ProductResponse;
import com.example.mikez.festpaycustomer.util.Preference;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private int creditnum = 9999;
    private Preference preference;

    //pay history products logout
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView creditNumberText = (TextView) findViewById(R.id.main_text_creditnumber);
        creditNumberText.setText("You have " + String.valueOf(creditnum) + " credits");
        Button buttonPay = (Button) findViewById(R.id.main_button_pay);
        Button buttonHistory = (Button) findViewById(R.id.main_button_history);
        Button buttonProducts = (Button) findViewById(R.id.main_button_products);
        Button buttonLogOut = (Button) findViewById(R.id.main_button_logout);
        preference = new Preference(this);

        buttonPay.setOnClickListener(this);
        buttonHistory.setOnClickListener(this);
        buttonProducts.setOnClickListener(this);
        buttonLogOut.setOnClickListener(this);



    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_button_logout:
                preference.setPreference(Preference.KEY_REMEBER, false);
                Intent intentBack = new Intent(this, LoginActivity.class);
                startActivity(intentBack);
                finish();
                break;
            case R.id.main_button_pay:
                Intent intentPay = new Intent(this, PayActivity.class);
                startActivity(intentPay);
                break;
            case R.id.main_button_history:
                Intent intentHistory = new Intent(this, HistoryActivity.class);
                startActivity(intentHistory);
                break;
            case R.id.main_button_products:
                Intent intentProducts = new Intent(this, ProductsActivity.class);
                startActivity(intentProducts);
                break;

        }

    }



}
