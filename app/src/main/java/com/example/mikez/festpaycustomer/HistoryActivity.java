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

public class HistoryActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView imageBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        imageBack = (ImageView) findViewById(R.id.history_button_back);
        imageBack.setOnClickListener(this);

        List<Info> data = new ArrayList<>();
        data.add(new Info(1, "This is the first row"));
        data.add(new Info(2, "This is the second row"));
        data.add(new Info(3, "This is the third row"));
        data.add(new Info(4, "This is the fourth row"));
        data.add(new Info(5, "This is the fifth row"));

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
