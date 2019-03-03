package com.starkogi.mpesa;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.starkogi.mpesa.Interceptors.AccessTockenHttpInterceptor;
import com.starkogi.mpesa.Interceptors.MpesaRequestAuthHttpInterceptor;
import com.starkogi.mpesa.Models.AccessToken;
import com.starkogi.mpesa.Models.STKPushData;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RequestClient {

    private String ConsumerKey;
    private String ConsumerSecret;
    private String PassKey;
    private Retrofit retrofit;
    private Interceptor interceptor;
    private AccessToken accessToken;

    public RequestClient(String consumerKey, String consumerSecret, String passKey) {
        ConsumerKey = consumerKey;
        ConsumerSecret = consumerSecret;
        PassKey = passKey;
    }

    public RequestClient(Interceptor interceptor) {
        this.interceptor = interceptor;
    }

    public ApiEndpointServices getInstance() {



        OkHttpClient okhttpBuilder = okHttpClient().build();

        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(new Utils().getMpesaBaseUrl())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okhttpBuilder)
                    .build();

        }

        return retrofit.create(ApiEndpointServices.class);
    }


    private OkHttpClient.Builder okHttpClient() {
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        okHttpClient
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .addInterceptor( interceptor);

        return okHttpClient;
    }

    public void createPushSTK(final STKPushData stkPushData){

        stkPushData.setPassword(new Utils().generateMpesaB64Password(stkPushData.getBusinessShortCode(), PassKey, stkPushData.getTimestamp()));

        //Check if the Access Token exists | and if not expired

        if(accessToken != null){

            //Proceed to push the stk
            push(stkPushData);
            return;
        }

        new RequestClient(new AccessTockenHttpInterceptor(ConsumerKey, ConsumerSecret)).getInstance().getAccessToken().enqueue(new Callback<AccessToken>() {
            @Override
            public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
                Log.i("AccessToken ", new Gson().toJson(response.body()));

                //Check if request status is successful
                if(response.code() == 200){
                    accessToken = response.body();
                    push(stkPushData);

                }

            }

            @Override
            public void onFailure(Call<AccessToken> call, Throwable t) {
                Log.i("AccessToken Error : ",t.getMessage());

            }
        });

    }

    private void push(final STKPushData stkPushData) {

        Log.i("Json Response ", new Gson().toJson(stkPushData));

        new RequestClient(new MpesaRequestAuthHttpInterceptor(accessToken)).getInstance().postRequest(stkPushData).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.i("STKPush Response ", new Gson().toJson(response.message()));
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.i("STKPush Error ", t.getMessage());

            }
        });
    }
}
