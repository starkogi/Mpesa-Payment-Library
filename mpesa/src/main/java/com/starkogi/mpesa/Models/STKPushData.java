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

    public STKPushData(String amount, String partyA, String phoneNumber, String accountReference,
                       String transactionDesc) {
        Amount = amount;
        PartyA = partyA;
        PhoneNumber = new Utils().getMpesaFormatedPhoneNumber(phoneNumber);
        AccountReference = accountReference;
        TransactionDesc = transactionDesc;

        setUpDefaults();
    }

    private void setUpDefaults() {

        BusinessShortCode = new Utils().getBusinessShortCode();
        TransactionType = new Utils().getTransactionType();
        PartyB= new Utils().getBusinessShortCode();
        CallBackURL= new Utils().getCallBackURL();

        Timestamp = new Utils().getTimestamp();
        Password = new Utils().generateMpesaB64Password(BusinessShortCode,BuildConfig.PASSKEY,Timestamp);


    }
}
