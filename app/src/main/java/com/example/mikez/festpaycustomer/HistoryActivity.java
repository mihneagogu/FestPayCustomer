package com.example.mikez.festpaycustomer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.mikez.festpaycustomer.adapters.HistoryAdapter;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView imageBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        imageBack = (ImageView) findViewById(R.id.history_button_back);
        imageBack.setOnClickListener(this);

        List<InfoHistory> data = new ArrayList<>();
        data.add(new InfoHistory(1, "Coca-cola", "coca", "2 baneti", "ba"));
        data.add(new InfoHistory(2, "Coca-cola", "coddca", "3 baneti", "ba"));
        data.add(new InfoHistory(3, "ddd", " sdd", "sdsd", "ba"));
        data.add(new InfoHistory(4, "ddd", " sdsd", "sdssd", "ba"));
        data.add(new InfoHistory(5, "ddd", " sdd", "sdsd", "ba"));

        HistoryAdapter adapter = new HistoryAdapter(this, data);
        RecyclerView recycleList = (RecyclerView) findViewById(R.id.history_recycler_view);
        recycleList.setLayoutManager(new LinearLayoutManager(this));
        recycleList.setAdapter(adapter);

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.history_button_back:
                finish();

        }
    }
}
