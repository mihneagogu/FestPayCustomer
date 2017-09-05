package com.example.mikez.festpaycustomer;

import android.content.Intent;
import android.icu.text.IDNA;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mikez.festpaycustomer.adapters.HistoryAdapter;
import com.example.mikez.festpaycustomer.adapters.ProductsAdapter;
import com.example.mikez.festpaycustomer.localdatabase.Product;
import com.example.mikez.festpaycustomer.localdatabase.ProductManager;

import java.util.ArrayList;
import java.util.List;

public class ProductsActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView imageBack;
    private ImageView imageSearch;
    private AutoCompleteTextView autoCompleteTextSearch;
    private RecyclerView recycleList;

    private ProductManager database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        imageSearch = (ImageView) findViewById(R.id.products_image_search);
        imageBack = (ImageView) findViewById(R.id.products_button_back);
        autoCompleteTextSearch = (AutoCompleteTextView) findViewById(R.id.products_autocomplete_text_view_search);
        imageBack.setOnClickListener(this);
        imageSearch.setOnClickListener(this);






        database = new ProductManager(this);
        List<InfoProducts> products = database.registerProduct("cola", "Gigi", 10);
        List<String> searchOptions;
        searchOptions = database.getSearchOptions();
        ArrayAdapter<String> autoCompleteAdapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, searchOptions);
        autoCompleteTextSearch.setThreshold(3);
        autoCompleteTextSearch.setAdapter(autoCompleteAdapter);

        ProductsAdapter adapter = new ProductsAdapter(this, products);
        recycleList = (RecyclerView) findViewById(R.id.products_recycler_view);
        recycleList.setLayoutManager(new LinearLayoutManager(this));
        recycleList.setAdapter(adapter);

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.products_button_back:
                finish();
                break;
            case R.id.products_image_search:
                String name, vendor;
                int price;
                List<Product> productBySearch = database.searchProduct(autoCompleteTextSearch.getText().toString());
                List <InfoProducts> productsAfterSearch = new ArrayList<>();
                for (Product x: productBySearch){
                    name = x.getName();
                    vendor = x.getVendor();
                    price = x.getPrice();
                    productsAfterSearch.add(new InfoProducts(name, vendor,price));
                }
                ProductsAdapter adapterAfterSearch = new ProductsAdapter(this, productsAfterSearch);
                recycleList.setLayoutManager(new LinearLayoutManager(this));
                recycleList.setAdapter(adapterAfterSearch);
                break;
        }
    }
}
