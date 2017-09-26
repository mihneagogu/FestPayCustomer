package com.example.mikez.festpaycustomer.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;

import com.example.mikez.festpaycustomer.R;
import com.example.mikez.festpaycustomer.adapters.ProductsAdapter;
import com.example.mikez.festpaycustomer.localdatabase.Product;
import com.example.mikez.festpaycustomer.localdatabase.ProductManager;
import com.example.mikez.festpaycustomer.network.ProductModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mikez on 9/26/2017.
 */

public class ProductsFragment extends Fragment implements View.OnClickListener {

    private ImageView imageFilter;
    private ImageView imageBack;
    private ImageView imageSearch;
    private AutoCompleteTextView autoCompleteTextSearch;
    private RecyclerView recycleList;
    private ProductManager database;


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        imageFilter = (ImageView) getActivity().findViewById(R.id.products_image_filter);
        imageSearch = (ImageView) getActivity().findViewById(R.id.products_image_search);
        autoCompleteTextSearch = (AutoCompleteTextView) getActivity().findViewById(R.id.products_autocomplete_text_view_search);
        imageFilter.setOnClickListener(this);
        imageSearch.setOnClickListener(this);




        database = new ProductManager(getContext());
        List<String> searchOptions;
        searchOptions = database.getSearchOptions();
        ArrayAdapter<String> autoCompleteAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.select_dialog_item, searchOptions);
        autoCompleteTextSearch.setThreshold(3);
        autoCompleteTextSearch.setAdapter(autoCompleteAdapter);
        List<ProductModel> products = database.getProducts();
        List<Product> productsForView = new ArrayList<>();
        for (ProductModel x : products){
            productsForView.add(new Product(x.getName(), x.getShop(), x.getPrice()));
        }

        ProductsAdapter adapter = new ProductsAdapter(getContext(), productsForView);
        recycleList = (RecyclerView) getActivity().findViewById(R.id.products_recycler_view);
        recycleList.setLayoutManager(new LinearLayoutManager(getContext()));
        recycleList.setAdapter(adapter);
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
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
                ProductsAdapter adapterAfterSearch = new ProductsAdapter(getContext(), productsAfterSearch);
                recycleList.setLayoutManager(new LinearLayoutManager(getContext()));
                recycleList.setAdapter(adapterAfterSearch);
                break;
            case R.id.products_image_filter:
                List<Product> productsByFilter = database.orderAlphabetically();
//                List<Product> productsAfterFilter = new ArrayList<>();
//                for (Product x: productsByFilter){
//                    productsAfterFilter.add(x.getName());
//                    productsAfterFilter.add(x.getVendor());
//                    productsAfterFilter.add(x.getPrice());
//                }
                ProductsAdapter adapterAfterFilter = new ProductsAdapter(getContext(),productsByFilter);
                recycleList.setLayoutManager(new LinearLayoutManager(getContext()));
                recycleList.setAdapter(adapterAfterFilter);
                break;
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_products, container, false);
    }
}
