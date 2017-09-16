package com.example.mikez.festpaycustomer;

import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TransactionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);


        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }





    @Override
    protected void onResume() {
        super.onResume();

        Intent intent = getIntent();
        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(intent.getAction())) {
            Parcelable[] rawMessages = intent.getParcelableArrayExtra(
                    NfcAdapter.EXTRA_NDEF_MESSAGES);
            NdefMessage message = (NdefMessage) rawMessages[0];
//            textNfc.setText(new String(message.getRecords()[0].getPayload()));
            decodeMessage(new String(message.getRecords()[0].getPayload()));

        }
    }

    public void decodeMessage(String message){
        List<String> transferredData = Arrays.asList(message.split("_;_"));
        double credits = Double.parseDouble(transferredData.get(0));
        String vendor = transferredData.get(2);
        List<InfoHistory> historyModels = new ArrayList<>();
        List<String> products = Arrays.asList(transferredData.get(1).split("__;__"));
        for (String x : products){
            List<String> items = Arrays.asList(x.split("___;___"));
            historyModels.add(new InfoHistory(items.get(0), items.get(1), items.get(2), items.get(3)));

        }
        // update credits and history
        finish();
    }
}
