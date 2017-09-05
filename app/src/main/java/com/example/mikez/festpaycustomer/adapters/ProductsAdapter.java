package com.example.mikez.festpaycustomer.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mikez.festpaycustomer.InfoProducts;
import com.example.mikez.festpaycustomer.R;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by mikez on 7/18/2017.
 */


public class ProductsAdapter extends RecyclerView.Adapter<com.example.mikez.festpaycustomer.adapters.ProductsAdapter.ViewHolder> {
    private Context context;
    private List<InfoProducts> data2;

    public ProductsAdapter(Context context, List<InfoProducts> data) {
        this.context = context;
        this.data2 = data;
    }


    @Override
    public com.example.mikez.festpaycustomer.adapters.ProductsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.custom_row_products, parent, false);
        return new com.example.mikez.festpaycustomer.adapters.ProductsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(com.example.mikez.festpaycustomer.adapters.ProductsAdapter.ViewHolder holder, int position) {
        InfoProducts currentData = data2.get(position);
        int priceInt = currentData.getPrice();
        String priceString = String.valueOf(priceInt);
        holder.name.setText(currentData.getName());
        holder.vendor.setText(currentData.getVendor());
        holder.price.setText(priceString);

    }

    @Override
    public int getItemCount() {
        return data2.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView price;
        TextView vendor;

        public ViewHolder(View itemView) {

            super(itemView);
            name = (TextView) itemView.findViewById(R.id.product_name);
            vendor = (TextView) itemView.findViewById(R.id.product_vendor);
            price = (TextView) itemView.findViewById(R.id.product_price);

        }


    }

}
