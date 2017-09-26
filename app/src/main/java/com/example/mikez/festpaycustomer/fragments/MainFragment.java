package com.example.mikez.festpaycustomer.fragments;


import android.content.Intent;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mikez.festpaycustomer.LoginActivity;
import com.example.mikez.festpaycustomer.R;
import com.example.mikez.festpaycustomer.localdatabase.ProductManager;
import com.example.mikez.festpaycustomer.network.NetworkManager;
import com.example.mikez.festpaycustomer.network.ProductModel;
import com.example.mikez.festpaycustomer.network.ProductResponse;
import com.example.mikez.festpaycustomer.util.CreditPreference;
import com.example.mikez.festpaycustomer.util.Preference;
import android.support.v4.app.Fragment;

import java.util.List;

/**
 * Created by mikez on 9/26/2017.
 */

public class MainFragment extends android.support.v4.app.Fragment implements View.OnClickListener {

    private double creditnum = 9999;
    private Button buttonLogOut;
    private Preference preference;
    private CreditPreference creditPreference;
    private ProductManager database;
    private NetworkManager network;
    private static final String KEY_CREDITS = "credits";
    private TextView creditNumberText;


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



        creditNumberText = (TextView) getActivity().findViewById(R.id.main_fragment_text_credit_number);
        buttonLogOut = (Button) getActivity().findViewById(R.id.main_fragment_button_log_out);

        preference = new Preference(getActivity());
        creditPreference = new CreditPreference(getActivity());
        database = new ProductManager(getActivity());
        setCredits();

        getActivity().findViewById(R.id.main_fragment_button_log_out).setOnClickListener(this);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.main_fragment_button_log_out){
            preference.setPreference(Preference.KEY_REMEBER, false);
            Intent intentBack = new Intent(getActivity(), LoginActivity.class);
            startActivity(intentBack);
        }
    }

    public void setCredits(){
        System.out.println("SET CREDITS METHOD: " + creditPreference.getStringPreference(KEY_CREDITS));
        creditNumberText.setText(creditPreference.getStringPreference(KEY_CREDITS));
    }

}
