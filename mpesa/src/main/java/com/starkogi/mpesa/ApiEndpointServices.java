package com.starkogi.mpesa;

import com.google.gson.JsonObject;
import com.starkogi.mpesa.Models.AccessToken;
import com.starkogi.mpesa.Models.STKPushData;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiEndpointServices {

    @POST("mpesa/stkpush/v1/processrequest")
    Call<JsonObject> postRequest(@Body STKPushData stkPush);

    @GET("oauth/v1/generate?grant_type=client_credentials")
    Call<AccessToken> getAccessToken();

}
