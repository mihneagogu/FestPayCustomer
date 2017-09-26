package com.example.mikez.festpaycustomer.fragments;

import android.app.Activity;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcEvent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.example.mikez.festpaycustomer.R;
import com.example.mikez.festpaycustomer.adapters.ViewPagerAdapter;
import com.example.mikez.festpaycustomer.localdatabase.HistoryManager;
import com.example.mikez.festpaycustomer.localdatabase.ProductManager;
import com.example.mikez.festpaycustomer.network.HistoryModel;
import com.example.mikez.festpaycustomer.network.NetworkManager;
import com.example.mikez.festpaycustomer.network.ProductModel;
import com.example.mikez.festpaycustomer.network.ProductResponse;
import com.example.mikez.festpaycustomer.util.CreditPreference;
import com.example.mikez.festpaycustomer.util.Preference;

import java.util.ArrayList;
import java.util.List;

public class FirstActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener, NfcAdapter.CreateNdefMessageCallback, ProductResponse {
    private ViewPager pager;
    private TabLayout tab;
    private ProductManager database;
    private CreditPreference creditPreference;
    private NetworkManager network;
    private HistoryManager history;
    private List<HistoryModel> historyProducts;

    private Preference preference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        pager = (ViewPager) findViewById(R.id.container_main);
        setupViewPager();

        historyProducts = new ArrayList<>();
        historyProducts.add(new HistoryModel("Salata vegana", 15, 2, 30));
        historyProducts.add(new HistoryModel("Salata Caesar", 16, 1, 16));
        historyProducts.add(new HistoryModel("Mititei cu cas", 5, 4, 20));
        history = new HistoryManager(this);
        history.addHistory(historyProducts);
        network = new NetworkManager(this, NetworkManager.KEY_PRODUCT);
        network.getProducts();

        database = new ProductManager(this);
        preference = new Preference(this);
        creditPreference = new CreditPreference(this);

        tab = (TabLayout) findViewById(R.id.tab_main);
        tab.setupWithViewPager(pager);
        tab.addOnTabSelectedListener(this);
        setupTabIcons();



        NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if (nfcAdapter == null) {
            Toast.makeText(this, "Your device does not have NFC.", Toast.LENGTH_SHORT).show();
        } else if (nfcAdapter.isEnabled()) {
            Toast.makeText(this, "Waiting for connectivity.", Toast.LENGTH_SHORT).show();
            nfcAdapter.setNdefPushMessageCallback(this, this);

            //code goes here
            String message = 3 + "_;_" + "Mihnea" + "_;_" + true;
            preference.setPreference(Preference.KEY_NFC_DATA, message);

        } else {
            //experimental
            Toast.makeText(this, "Please turn on NFC.", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(android.provider.Settings.ACTION_NFC_SETTINGS));
        }
    }

    private void setupTabIcons() {
        tab.getTabAt(0).setIcon(R.drawable.main_selected);
        tab.getTabAt(1).setIcon(R.drawable.products_icon);
        tab.getTabAt(2).setIcon(R.drawable.history_icon);

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        hideKeyboard();
        switch (tab.getPosition()) {
            case 0:
                tab.setIcon(R.drawable.main_selected);
                break;
            case 1:
                tab.setIcon(R.drawable.products_selected);
                break;
            case 2:
                tab.setIcon(R.drawable.history_selected);
                break;
        }
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        switch (tab.getPosition()) {
            case 0:
                tab.setIcon(R.drawable.main_icon);
                break;
            case 1:
                tab.setIcon(R.drawable.products_icon);
                break;
            case 2:
                tab.setIcon(R.drawable.history_icon);
                break;
        }

    }

    private void setupViewPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new MainFragment());
        adapter.addFragment(new ProductsFragment());
        adapter.addFragment(new HistoryFragment());
        pager.setAdapter(adapter);
    }

    @Override
    public NdefMessage createNdefMessage(NfcEvent event) {
        String message = 3 + "_;_" + "Mihnea" + "_;_" + true;
        NdefRecord ndefRecord = NdefRecord.createMime("text/plain", message.getBytes());
        return new NdefMessage(ndefRecord);
    }

    private void hideKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (getCurrentFocus() != null) {
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    @Override
    public void loadProducts(List<ProductModel> productModels) {
        System.out.println("LOADING PRODUCTS!!!");
        if (database.getProducts().size() > 0) {
            database.deleteAll();
        }
            database.registerProduct(productModels);

    }

    @Override
    public void updateUser(int credits) {
        System.out.println("CREDITS           " + credits);
        creditPreference.setPreference(CreditPreference.KEY_CREDITS, String.valueOf(credits));
    }

    @Override
    protected void onResume() {
        super.onResume();
        network.updateUser();
    }
}
