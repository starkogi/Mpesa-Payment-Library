package com.starkogi.kogilibraries;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.starkogi.mpesa.Interceptors.AccessTockenHttpInterceptor;
import com.starkogi.mpesa.Interceptors.MpesaRequestAuthHttpInterceptor;
import com.starkogi.mpesa.Models.AccessToken;
import com.starkogi.mpesa.Models.STKPushData;
import com.starkogi.mpesa.RequestClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        callAPI();
    }

    private void callAPI() {


        STKPushData stkPushData = new STKPushData( "100", "254727269588", "0727269588", "StarKogi",
                "Paying For Violets pedicure");

        new RequestClient().createPushSTK(stkPushData);

    }
}
