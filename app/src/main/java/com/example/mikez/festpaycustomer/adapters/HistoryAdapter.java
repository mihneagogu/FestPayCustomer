package com.example.mikez.festpaycustomer.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mikez.festpaycustomer.Info;
import com.example.mikez.festpaycustomer.R;

import java.util.List;

/**
 * Created by mikez on 7/18/2017.
 */

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder>{
    private Context context;
    private List<Info> data;

    public HistoryAdapter(Context context, List<Info> data) {
        this.context = context;
        this.data = data;
    }


    @Override
    public HistoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.custom_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Info currentData = data.get(position);
        holder.text.setText(currentData.getItemText());
        holder.icon.setImageDrawable(context.getResources().getDrawable(R.drawable.icon_1));

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView text;
        ImageView icon;

        public ViewHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.history_recycler_text);
            icon = (ImageView) itemView.findViewById(R.id.history_recycler_icon);
        }


    }

}
