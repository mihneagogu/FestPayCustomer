package com.example.mikez.festpaycustomer.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mikez.festpaycustomer.R;
import com.example.mikez.festpaycustomer.adapters.HistoryAdapter;
import com.example.mikez.festpaycustomer.localdatabase.HistoryManager;

import java.util.ArrayList;

/**
 * Created by mikez on 9/26/2017.
 */

public class HistoryFragment extends Fragment implements View.OnClickListener{

    private HistoryManager database;

    public HistoryFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_history, container, false);
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        database = new HistoryManager(getContext());



        HistoryAdapter adapter = new HistoryAdapter(getContext(), database.getHistory());
        RecyclerView recycleList = (RecyclerView) getActivity().findViewById(R.id.history_recycler_view);
        recycleList.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycleList.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {

    }
}
