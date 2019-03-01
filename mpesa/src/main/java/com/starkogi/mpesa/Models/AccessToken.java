package com.starkogi.mpesa.Models;

public class AccessToken {
    private String access_token, expires_in;

    public AccessToken(String access_token, String expires_in) {
        this.access_token = access_token;
        this.expires_in = expires_in;
    }
}
