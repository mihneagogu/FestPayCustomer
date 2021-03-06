package com.example.mikez.festpaycustomer.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mikez.festpaycustomer.R;
import com.example.mikez.festpaycustomer.network.HistoryModel;

import java.util.List;

/**
 * Created by mikez on 7/18/2017.
 */

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder>{
    private Context context;
    private List<HistoryModel> data;

    public HistoryAdapter(Context context, List<HistoryModel> data) {
        this.context = context;
        this.data = data;
    }


    @Override
    public HistoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.custom_row_history, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        HistoryModel currentData = data.get(position);
        holder.name.setText(currentData.getName());
        holder.price.setText(String.valueOf(currentData.getPrice()));
        holder.quantity.setText(String.valueOf(currentData.getQuantity()));
        holder.finalPrice.setText(String.valueOf(currentData.getFinalPrice()));

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView price;
        TextView quantity;
        TextView finalPrice;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.history_product_name);
            price = (TextView) itemView.findViewById(R.id.history_product_price);
            quantity = (TextView) itemView.findViewById(R.id.history_product_quantity);
            finalPrice = (TextView) itemView.findViewById(R.id.history_product_final_price);
        }


    }

}
