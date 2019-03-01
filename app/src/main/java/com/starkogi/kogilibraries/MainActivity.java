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


         new RequestClient(new AccessTockenHttpInterceptor()).getInstance().getAccessToken().enqueue(new Callback<AccessToken>() {
            @Override
            public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
                Log.i("AccessToken ", new Gson().toJson(response.body()));
            }

            @Override
            public void onFailure(Call<AccessToken> call, Throwable t) {
                Log.i("AccessToken Error : ",t.getMessage());

            }
        });

        STKPushData stkPushData = new STKPushData( "100", "254727269588", "0727269588", "StarKogi",
                "Paying For Violets pedicure");

        Log.i("STKPushData ", new Gson().toJson(stkPushData));


        new RequestClient(new MpesaRequestAuthHttpInterceptor()).getInstance().postRequest(stkPushData).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.i("JsonObject ", String.valueOf(response.code()));
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.i("JsonObject Error ", t.getMessage());

            }
        });
    }
}
