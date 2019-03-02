package com.starkogi.mpesa.Interceptors;

import com.starkogi.mpesa.Models.AccessToken;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class MpesaRequestAuthHttpInterceptor implements Interceptor {

    AccessToken accessToken;

    public MpesaRequestAuthHttpInterceptor(AccessToken accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request  = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer " + accessToken.getAccess_token())
                .build();
        return chain.proceed(request);

    }
}
