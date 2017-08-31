package com.example.mikez.festpaycustomer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mikez.festpaycustomer.localdatabase.Product;
import com.example.mikez.festpaycustomer.localdatabase.ProductManager;

import java.util.List;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editSearch;
    private Button buttonSearch;
    private TextView textResults;
    private ProductManager database;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        editSearch = (EditText) findViewById(R.id.search_edit_search);
        buttonSearch = (Button) findViewById(R.id.search_button_execute_search);
        textResults = (TextView) findViewById(R.id.search_text_results);

        database = new ProductManager(this);

        buttonSearch.setOnClickListener(this);
    }

    public void onClick(View view){
        if (view.getId() == R.id.search_button_execute_search) {
            List<Product> productBySearch = database.searchProduct(editSearch.getText().toString());
            String builder = "";
            for (Product x: productBySearch){
                builder += "Name: " + x.getName() + "\n";
                builder += "Vendor: " + x.getVendor() + "\n";
                builder += "Price: " + x.getPrice() + "\n";
                builder += "---------------------\n";
            }
            textResults.setText(builder);
        }

    }
}
