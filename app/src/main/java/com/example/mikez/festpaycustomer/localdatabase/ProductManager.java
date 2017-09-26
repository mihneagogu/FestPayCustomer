package com.example.mikez.festpaycustomer.localdatabase;

import android.content.Context;
import android.database.Cursor;

import com.example.mikez.festpaycustomer.network.ProductModel;

import java.util.ArrayList;
import java.util.List;


import static com.example.mikez.festpaycustomer.localdatabase.DatabaseContract.CURSOR_PRODUCT_ID;
import static com.example.mikez.festpaycustomer.localdatabase.DatabaseContract.CURSOR_PRODUCT_NAME;
import static com.example.mikez.festpaycustomer.localdatabase.DatabaseContract.CURSOR_PRODUCT_PRICE;
import static com.example.mikez.festpaycustomer.localdatabase.DatabaseContract.CURSOR_PRODUCT_VENDOR;

/**
 * Created by mikez on 8/31/2017.
 */

public class ProductManager {

    private Context context;
    private int productNumbers;
    private DatabaseHelper database;
    private List<ProductModel> products;
    private int id;
    private List<String> optionsSearch = new ArrayList<>();

    public ProductManager(Context context) {
        products = new ArrayList<>();
        this.context = context;
        setDatabase(new DatabaseHelper(context));
    }


    public void registerProduct(List<ProductModel> products) {
        int id = 0;
        for (ProductModel x : products) {
            ++id;
            database.addProduct(x.getName(), x.getShop(), x.getPrice(), x.getId());
            System.out.println("ID:  ------------- " + x.getName() + "//////");
        }
        setMaxId(id);
    }

    public List<Product> orderAlphabetically() {
        Cursor cursor = getDatabase().getOrderedProducts();
        List<Product> products = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                products.add(new Product(cursor.getString(CURSOR_PRODUCT_NAME), cursor.getString(CURSOR_PRODUCT_VENDOR), cursor.getInt(CURSOR_PRODUCT_ID)));
            } while (cursor.moveToNext());
        }
        return products;
    }

    public void setMaxId(int id) {
        this.id = id;
    }

    public int getMaxId() {
        return id;
    }

    public List<ProductModel> getProducts() {
        List<ProductModel> result = new ArrayList<>();
        Cursor cursor = database.getProducts();
        if (cursor.moveToFirst()) {
            do {
                result.add(new ProductModel(cursor.getString(CURSOR_PRODUCT_NAME), cursor.getString(CURSOR_PRODUCT_VENDOR), Double.parseDouble(cursor.getString(CURSOR_PRODUCT_PRICE)), cursor.getInt(CURSOR_PRODUCT_ID)));
            } while (cursor.moveToNext());
        }
        return result;
    }

//    public void saveImage(String name) {
//        ContextWrapper cw = new ContextWrapper(getContext());
//        File directory = cw.getDir("images", Context.MODE_PRIVATE);
//        File myPath = new File(directory, name + ".jpg");
//        FileOutputStream fos = null;
//        try {
//            fos = new FileOutputStream(myPath);
//            getImage().compress(Bitmap.CompressFormat.JPEG, 100, fos);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                assert fos != null;
//                fos.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public Bitmap getImage(String name) {
//        Bitmap bitmap = null;
//        ContextWrapper cw = new ContextWrapper(getContext());
//        File directory = cw.getDir("images", Context.MODE_PRIVATE);
//        try {
//            File f = new File(directory, name + ".jpg");
//            bitmap = BitmapFactory.decodeStream(new FileInputStream(f));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        return bitmap;
//    }

    public List<String> getSearchOptions() {
        return optionsSearch;
    }

    public List<Product> searchProduct(String productName) {
        Cursor cursor = getDatabase().getProductForSearch(productName);
        List<Product> products = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                products.add(new Product(cursor.getString(DatabaseContract.CURSOR_PRODUCT_NAME), cursor.getString(DatabaseContract.CURSOR_PRODUCT_VENDOR), cursor.getInt(CURSOR_PRODUCT_PRICE)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        productNumbers = products.size();
        return products;

    }

    public void deleteAll() {
        getDatabase().deleteDatabase();
    }

    public void setDatabase(DatabaseHelper database) {

        this.database = database;

    }

    public DatabaseHelper getDatabase() {
        return database;
    }

    public Context getContext() {
        return context;
    }
}

