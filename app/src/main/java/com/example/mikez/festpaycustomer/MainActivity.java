//package com.example.mikez.festpaycustomer;
//
//import android.content.Intent;
//import android.nfc.NdefMessage;
//import android.nfc.NdefRecord;
//import android.nfc.NfcAdapter;
//import android.nfc.NfcEvent;
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.view.View;
//import android.widget.Button;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.example.mikez.festpaycustomer.localdatabase.HistoryManager;
//import com.example.mikez.festpaycustomer.localdatabase.Product;
//import com.example.mikez.festpaycustomer.localdatabase.ProductManager;
//import com.example.mikez.festpaycustomer.network.HistoryModel;
//import com.example.mikez.festpaycustomer.network.NetworkManager;
//import com.example.mikez.festpaycustomer.network.ProductModel;
//import com.example.mikez.festpaycustomer.network.ProductResponse;
//import com.example.mikez.festpaycustomer.util.CreditPreference;
//import com.example.mikez.festpaycustomer.util.Preference;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class MainActivity extends AppCompatActivity implements View.OnClickListener, ProductResponse, NfcAdapter.CreateNdefMessageCallback {
//
//    private double creditnum = 9999;
//    private Preference preference;
//    private CreditPreference creditPreference;
//    private ProductManager database;
//    private NetworkManager network;
//    private static final String KEY_CREDITS = "credits";
//    private TextView creditNumberText;
//
//
//    //pay history products logout
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//
//
//        creditNumberText = (TextView) findViewById(R.id.main_text_creditnumber);
//        Button buttonPay = (Button) findViewById(R.id.main_button_pay);
//        Button buttonHistory = (Button) findViewById(R.id.main_button_history);
//        Button buttonProducts = (Button) findViewById(R.id.main_button_products);
//        Button buttonLogOut = (Button) findViewById(R.id.main_button_logout);
//
//        preference = new Preference(this);
//        creditPreference = new CreditPreference(this);
//        network = new NetworkManager(this, NetworkManager.KEY_PRODUCT);
//        database = new ProductManager(this);
//        if (database.getProducts().size() > 0) {
////            delete and repopulate
//            System.out.println("SIZEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE \n\n\n\n\n\n ----------------- \n asdasdasd" + database.getProducts().size());
//            database.deleteAll();
//            network.getProducts();
//        } else {
//            network.getProducts();
//        }
//
//        creditPreference.setPreference(KEY_CREDITS, "3");
//        creditnum -= Double.parseDouble(creditPreference.getStringPreference(KEY_CREDITS));
//        creditNumberText.setText(String.valueOf(creditnum));
//
//        buttonPay.setOnClickListener(this);
//        buttonHistory.setOnClickListener(this);
//        buttonProducts.setOnClickListener(this);
//        buttonLogOut.setOnClickListener(this);
//
//        NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(this);
//        if (nfcAdapter == null) {
//            Toast.makeText(this, "Your device does not have NFC.", Toast.LENGTH_SHORT).show();
//        } else if (nfcAdapter.isEnabled()) {
//            Toast.makeText(this, "Waiting for connectivity.", Toast.LENGTH_SHORT).show();
//            nfcAdapter.setNdefPushMessageCallback(this, this);
//
//            //code goes here
//            String message = 3 + "_;_" + "Mihnea" + "_;_" + true;
//            preference.setPreference(Preference.KEY_NFC_DATA, message);
//
//        } else {
//            //experimental
//            Toast.makeText(this, "Please turn on NFC.", Toast.LENGTH_SHORT).show();
//            startActivity(new Intent(android.provider.Settings.ACTION_NFC_SETTINGS));
//        }
//
//    }
//
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.main_button_logout:
//                preference.setPreference(Preference.KEY_REMEBER, false);
//                Intent intentBack = new Intent(this, LoginActivity.class);
//                startActivity(intentBack);
//                finish();
//                break;
//            case R.id.main_button_pay:
//                NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(this);
//                if (nfcAdapter == null) {
//                    Toast.makeText(this, "Your device does not have NFC.", Toast.LENGTH_SHORT).show();
//                } else if (nfcAdapter.isEnabled()) {
//                    Toast.makeText(this, "Waiting for connectivity.", Toast.LENGTH_SHORT).show();
//                    nfcAdapter.setNdefPushMessageCallback(this, this);
//                } else {
//                    //experimental
//                    Toast.makeText(this, "Please turn on NFC.", Toast.LENGTH_SHORT).show();
//                    startActivity(new Intent(android.provider.Settings.ACTION_NFC_SETTINGS));
//                }
//                break;
//            case R.id.main_button_history:
//                Intent intentHistory = new Intent(this, HistoryActivity.class);
//                startActivity(intentHistory);
//                break;
//            case R.id.main_button_products:
//                Intent intentProducts = new Intent(this, ProductsActivity.class);
//                startActivity(intentProducts);
//                break;
//
//        }
//
//    }
//
//
//    @Override
//    public void loadProducts(List<ProductModel> productModels) {
//        database.registerProduct(productModels);
//    }
//
//    @Override
//    public void updateUser(int credits) {
//        creditNumberText.setText(String.valueOf(credits));
//    }
//
//    @Override
//    public NdefMessage createNdefMessage(NfcEvent event) {
//        String message = 3 + "_;_" + "Mihnea" + "_;_" + true;
//        NdefRecord ndefRecord = NdefRecord.createMime("text/plain", message.getBytes());
//        return new NdefMessage(ndefRecord);
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        network.updateUser();
//    }
//}
