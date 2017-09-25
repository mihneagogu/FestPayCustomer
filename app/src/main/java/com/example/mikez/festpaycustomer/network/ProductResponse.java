package com.example.mikez.festpaycustomer.network;


import java.util.List;

/**
 * Created by AoD Akitektuo on 04-Sep-17 at 10:40.
 */

public interface ProductResponse {

    void loadProducts(List<ProductModel> productModels);

    void updateUser(int credits);

}
