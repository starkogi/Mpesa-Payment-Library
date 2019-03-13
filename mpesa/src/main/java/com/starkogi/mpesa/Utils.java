package com.starkogi.mpesa;

import android.util.Base64;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Utils {

    private static final String MPESA_BASE_URL = "https://sandbox.safaricom.co.ke/";
    private static final String MPESA_BASE_URL_PROD = "https://api.safaricom.co.ke/";


    //Expose the Url to other classes
    public static String getMpesaBaseUrl(boolean isProduction) {
        if (isProduction)
            return MPESA_BASE_URL_PROD;

        return MPESA_BASE_URL;


    }

    //Return Mpesa accepted Timestamp format
    public static String getTimestamp() {
        return new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).format(new Date());
    }

    //Generates Mpesa accepted phone number format
    public static String getMpesaFormatedPhoneNumber(String p_number) {

        if (p_number.equals("")) {
            return "";
        }

        if (p_number.length() < 11 & p_number.startsWith("0")) {
            String p = p_number.replaceFirst("^0", "254");
            return p;
        }
        if (p_number.length() == 13) {
            String p = p_number.replaceFirst("^+", "");
            return p;
        }
        return p_number;
    }

    //Generates Mpesa Password
    public static String generateMpesaB64Password(String businessShortCode, String passkey, String timestamp) {

        //Retun Base64 of the password
        return Base64.encodeToString((businessShortCode + passkey + timestamp).getBytes(), Base64.NO_WRAP);
    }

}
