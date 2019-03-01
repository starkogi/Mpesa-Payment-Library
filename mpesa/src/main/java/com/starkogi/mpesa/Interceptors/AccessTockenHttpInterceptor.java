package com.starkogi.mpesa.Interceptors;

import android.util.Base64;

import com.starkogi.mpesa.Utils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AccessTockenHttpInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {

        String keys = new Utils().getConsumerKey() + ":" + new Utils().getConsumerSecret() ;

        Request request = chain.request().newBuilder()
                .addHeader("Authorization", "Basic " + Base64.encodeToString(keys.getBytes(), Base64.NO_WRAP))
                .build();
        return chain.proceed(request);
    }
}
