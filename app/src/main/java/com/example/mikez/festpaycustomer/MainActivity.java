package com.example.mikez.festpaycustomer;

import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcEvent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mikez.festpaycustomer.localdatabase.Product;
import com.example.mikez.festpaycustomer.localdatabase.ProductManager;
import com.example.mikez.festpaycustomer.network.NetworkManager;
import com.example.mikez.festpaycustomer.network.ProductModel;
import com.example.mikez.festpaycustomer.network.ProductResponse;
import com.example.mikez.festpaycustomer.util.Preference;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ProductResponse, NfcAdapter.CreateNdefMessageCallback {

    private int creditnum = 9999;
    private Preference preference;
    private ProductManager database;
    private NetworkManager network;


    //pay history products logout
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        TextView creditNumberText = (TextView) findViewById(R.id.main_text_creditnumber);
        creditNumberText.setText("You have " + String.valueOf(creditnum) + " credits");
        Button buttonPay = (Button) findViewById(R.id.main_button_pay);
        Button buttonHistory = (Button) findViewById(R.id.main_button_history);
        Button buttonProducts = (Button) findViewById(R.id.main_button_products);
        Button buttonLogOut = (Button) findViewById(R.id.main_button_logout);
        preference = new Preference(this);

        network = new NetworkManager(this, NetworkManager.KEY_PRODUCT);
        database = new ProductManager(this);

        if (database.getProducts().size() > 0) {
//            delete and repopulate
        } else {
            network.getProducts();
        }

        buttonPay.setOnClickListener(this);
        buttonHistory.setOnClickListener(this);
        buttonProducts.setOnClickListener(this);
        buttonLogOut.setOnClickListener(this);



    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_button_logout:
                preference.setPreference(Preference.KEY_REMEBER, false);
                Intent intentBack = new Intent(this, LoginActivity.class);
                startActivity(intentBack);
                finish();
                break;
            case R.id.main_button_pay:
                NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(this);
                if (nfcAdapter == null) {
                    Toast.makeText(this, "Your device does not have NFC.", Toast.LENGTH_SHORT).show();
                } else if (nfcAdapter.isEnabled()) {
                    Toast.makeText(this, "Waiting for connectivity.", Toast.LENGTH_SHORT).show();
                    nfcAdapter.setNdefPushMessageCallback(this, this);
                } else {
                    //experimental
                    Toast.makeText(this, "Please turn on NFC.", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(android.provider.Settings.ACTION_NFC_SETTINGS));
                }
                break;
            case R.id.main_button_history:
                Intent intentHistory = new Intent(this, HistoryActivity.class);
                startActivity(intentHistory);
                break;
            case R.id.main_button_products:
                Intent intentProducts = new Intent(this, ProductsActivity.class);
                startActivity(intentProducts);
                break;

        }

    }


    @Override
    public void loadProducts(List<ProductModel> productModels) {
        database.registerProduct(productModels);
    }

    @Override
    public NdefMessage createNdefMessage(NfcEvent event) {
        String message = 3 + "_;_" + "Mihnea" + "_;_" + true;
        NdefRecord ndefRecord = NdefRecord.createMime("text/plain", message.getBytes());
        return new NdefMessage(ndefRecord);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
