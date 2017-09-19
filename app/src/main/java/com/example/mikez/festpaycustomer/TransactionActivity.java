package com.example.mikez.festpaycustomer;

import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mikez.festpaycustomer.localdatabase.HistoryManager;
import com.example.mikez.festpaycustomer.network.HistoryModel;
import com.example.mikez.festpaycustomer.util.CreditPreference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.alexrs.wavedrawable.WaveDrawable;

public class TransactionActivity extends AppCompatActivity {

    private HistoryManager database;
    private CreditPreference preference;
    private static final String KEY_CREDITS = "credits";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
        database = new HistoryManager(this);

        preference = new CreditPreference(this);

        ImageView imageNfc = (ImageView) findViewById(R.id.image_pay_nfc);
        ImageView imageBackground = (ImageView) findViewById(R.id.image_pay_background);
        WaveDrawable waveDrawable = new WaveDrawable(getResources().getColor(R.color.colorPrimary), 500);
        WaveDrawable waveBackground = new WaveDrawable(getResources().getColor(R.color.colorPrimary), 1000);
        imageNfc.setBackgroundDrawable(waveDrawable);
        imageBackground.setBackgroundDrawable(waveBackground);
        waveDrawable.setWaveInterpolator(new AccelerateDecelerateInterpolator());
        waveBackground.setWaveInterpolator(new AnticipateOvershootInterpolator());
        waveDrawable.startAnimation();
        waveBackground.startAnimation();


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
        double creditsToSubstract = 0;
        List<String> transferredData = Arrays.asList(message.split("_;_"));
        double credits = Double.parseDouble(transferredData.get(0));
        String vendor = transferredData.get(2);
        List<HistoryModel> historyModels = new ArrayList<>();
        List<String> products = Arrays.asList(transferredData.get(1).split("__;__"));
        for (String x : products){

            List<String> items = Arrays.asList(x.split("___;___"));
            historyModels.add(new HistoryModel((items.get(0)), Double.parseDouble(items.get(1)), Double.parseDouble(items.get(2)), Double.parseDouble(items.get(3))));
            creditsToSubstract += Double.parseDouble(items.get(3));

        }
        preference.setPreference(KEY_CREDITS, String.valueOf(creditsToSubstract));
        database.addHistory(historyModels);
        // update credits and history
        finish();
    }
}
