package com.example.mikez.festpaycustomer;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;

import com.example.mikez.festpaycustomer.adapters.ProductsAdapter;
import com.example.mikez.festpaycustomer.localdatabase.Product;
import com.example.mikez.festpaycustomer.localdatabase.ProductManager;
import com.example.mikez.festpaycustomer.network.ProductModel;

import java.util.ArrayList;
import java.util.List;

public class ProductsActivity extends AppCompatActivity implements View.OnClickListener {


    private ImageView imageFilter;
    private ImageView imageBack;
    private ImageView imageSearch;
    private AutoCompleteTextView autoCompleteTextSearch;
    private RecyclerView recycleList;

    private ProductManager database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        imageFilter = (ImageView) findViewById(R.id.products_image_filter);
        imageSearch = (ImageView) findViewById(R.id.products_image_search);
        imageBack = (ImageView) findViewById(R.id.products_button_back);
        autoCompleteTextSearch = (AutoCompleteTextView) findViewById(R.id.products_autocomplete_text_view_search);
        imageFilter.setOnClickListener(this);
        imageBack.setOnClickListener(this);
        imageSearch.setOnClickListener(this);






        database = new ProductManager(this);
        List<String> searchOptions;
        searchOptions = database.getSearchOptions();
        ArrayAdapter<String> autoCompleteAdapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, searchOptions);
        autoCompleteTextSearch.setThreshold(3);
        autoCompleteTextSearch.setAdapter(autoCompleteAdapter);
        List<ProductModel> products = database.getProducts();
        List<Product> productsForView = new ArrayList<>();
        for (ProductModel x : products){
            productsForView.add(new Product(x.getName(), x.getShop(), x.getPrice()));
        }

        ProductsAdapter adapter = new ProductsAdapter(this, productsForView);
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
                double price;
                List<Product> productBySearch = database.searchProduct(autoCompleteTextSearch.getText().toString());
                List <Product> productsAfterSearch = new ArrayList<>();
                for (Product x: productBySearch){
                    name = x.getName();
                    vendor = x.getVendor();
                    price = x.getPrice();
                    productsAfterSearch.add(new Product(name, vendor, price));
                }
                ProductsAdapter adapterAfterSearch = new ProductsAdapter(this, productsAfterSearch);
                recycleList.setLayoutManager(new LinearLayoutManager(this));
                recycleList.setAdapter(adapterAfterSearch);
                break;
        }
    }
}
