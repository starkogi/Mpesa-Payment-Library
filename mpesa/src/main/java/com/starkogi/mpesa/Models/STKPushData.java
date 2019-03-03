package com.starkogi.mpesa.Models;

import com.starkogi.mpesa.BuildConfig;
import com.starkogi.mpesa.Utils;

public class STKPushData {


    private String BusinessShortCode;
    private String Password;
    private String Timestamp;
    private String TransactionType;
    private String Amount;
    private String PartyA;
    private String PartyB;
    private String PhoneNumber;
    private String CallBackURL;
    private String AccountReference;
    private String TransactionDesc;


    public STKPushData(String businessShortCode, String transactionType, String amount,
                       String partyA, String callBackURL, String
                               accountReference, String transactionDesc) {
        BusinessShortCode = businessShortCode;
        TransactionType = transactionType;
        Amount = amount;
        PartyA = partyA;
        PartyB = businessShortCode;
        PhoneNumber = new Utils().getMpesaFormatedPhoneNumber(partyA);
        CallBackURL = callBackURL;
        AccountReference = accountReference;
        TransactionDesc = transactionDesc;

        Timestamp = new Utils().getTimestamp();

    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getBusinessShortCode() {
        return BusinessShortCode;
    }

    public String getTimestamp() {
        return Timestamp;
    }
}
