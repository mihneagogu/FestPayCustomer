package com.example.mikez.festpaycustomer.network;

import android.content.Context;


import com.example.mikez.festpaycustomer.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by AoD Akitektuo on 15-Sep-17 at 20:34.
 */

public class ParserJSON {

    private Context context;

    public ParserJSON(Context context) {
        this.context = context;
    }

    public List<ProductModel> convertJSONtoObject(String json) {
        List<ProductModel> productModel = new ArrayList<>();
        json = json.replaceAll("\\}", "_").replaceAll("\\{", "_");
        System.out.println(json);
        List<String> objectsAsString = Arrays.asList(json.split(context.getString(R.string.split)));
        objectsAsString.set(0, objectsAsString.get(0).substring(2, objectsAsString.get(0).length()));
        objectsAsString.set(objectsAsString.size() - 1, objectsAsString.get(objectsAsString.size() - 1).substring(0,
                objectsAsString.get(objectsAsString.size() - 1).length() - 2));
        for (String object : objectsAsString) {
            object = object.replaceAll("\":", "_;_").replaceAll(",\"", "__;__")
                    .replaceAll("\"", "")

                    //Delete this part of the code
                    .replaceAll("]", "").replaceAll("\\[", "");

            List<String> values = Arrays.asList(object.split("__;__"));
            productModel.add(new ProductModel(
                    Integer.parseInt(values.get(0).substring(values.get(0).indexOf("_;_") + 3)),
                    values.get(1).substring(values.get(1).indexOf("_;_") + 3),
                    values.get(7).substring(values.get(7).indexOf("_;_") + 3),
                    Double.parseDouble(values.get(3).substring(values.get(3).indexOf("_;_") + 3)),
                    values.get(2).substring(values.get(2).indexOf("_;_") + 3),
                    values.get(4).substring(values.get(4).indexOf("_;_") + 3),
                    values.get(5).substring(values.get(5).indexOf("_;_") + 3)));
        }
        return productModel;
    }

}
