package com.starkogi.kogilibraries;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.starkogi.mpesa.Models.STKPushData;
import com.starkogi.mpesa.RequestClient;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        callAPI();
    }

    private void callAPI() {


        STKPushData stkPushData = new STKPushData(
                BuildConfig.BUSINESS_SHORT_CODE,
                BuildConfig.TRANSACTION_TYPE,
                "100",
                "254727269588",
                BuildConfig.CALLBACK_URL,
                "StarKogi",
                "Paying For Violets pedicure"
        );

        new RequestClient(BuildConfig.CONSUMER_KEY, BuildConfig.CONSUMER_SECRET, BuildConfig.PASSKEY).createPushSTK(stkPushData);

    }
}
