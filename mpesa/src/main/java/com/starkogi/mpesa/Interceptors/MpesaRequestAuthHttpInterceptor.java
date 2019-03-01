package com.starkogi.mpesa.Interceptors;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class MpesaRequestAuthHttpInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request  = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer " + "K1pcciYYjEwA8H4w49RCAawCVuGs")
                .build();
        return chain.proceed(request);

    }
}
