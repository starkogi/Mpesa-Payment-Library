package com.starkogi.mpesa;

import com.starkogi.mpesa.Interceptors.AccessTockenHttpInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RequestClient {

    private Retrofit retrofit;
    Interceptor interceptor;

    public RequestClient(Interceptor interceptor) {
        this.interceptor = interceptor;
    }

    public ApiEndpointServices getInstance() {

        OkHttpClient okhttpBuilder = okHttpClient().build();
        okhttpBuilder.newBuilder().addInterceptor(new AccessTockenHttpInterceptor());
        okhttpBuilder.newBuilder().addInterceptor(new com.starkogi.mpesa.Interceptors.MpesaRequestAuthHttpInterceptor());

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

}
