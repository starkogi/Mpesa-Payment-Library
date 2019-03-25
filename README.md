# Mpesa Payment Library For Android
This repository contains the MPesa Android STK Payment Library that provides easy Mpesa STK Payment integration to android apps 

It currently includes the following Mpesa api
  For Lipa Na M-Pesa payment using STK Push. (M-PESA EXPRESS REQUEST)

Mpesa is a mobile payment platform from Safaricom and Mpesa Payment Library help you easily integrate MPesa payment when building your app . More information about Mpesa api can be found at https://developer.safaricom.co.ke/apis

# Requirements

Android Studio or any other prefered IDE.

JDK Version 1.7+

# Getting Started

You will need to get Mpesa API credentials from Daraja by Safaricom

	# Generate Mpesa Credentials
1. Create an account from Daraja (Safaricom API Developer Portal https://developer.safaricom.co.ke/login-register)
2. Create an App (Lipa na Mpesa Sandbox)
3. Select the App you created in (2) and get the #Consumer Key#	& #Consumer Secret#	 (Found under My Apps on the Navigation bar)
4. Get the test pass Key from https://developer.safaricom.co.ke/test_credentials (Lipa Na Mpesa Online PassKey:)

# Import StarKogi Mpesa Payment Library to Project

1. Add the following to your build.gradle (Project: YourProject) under repositories{} in allprojects{}
```

  maven { url 'https://jitpack.io' }
  
  like this 
  
  allprojects {
      repositories {
          ..................
          maven { url 'https://jitpack.io' }

      }
  }
```

2. Add the following to your build.gradle (Module: app) under android{}

//Please remember to replace the CONSUMER_KEY key and the CONSUMER_SECRET with the ones you got at (3) above (Generate Mpesa Credential)
//On going live please note that safaricom will provide you with new CONSUMER_KEY, CONSUMER_SECRET, BUSINESS_SHORT_CODE, PASSKEY and PARTYB.

```
buildTypes.each {
          it.buildConfigField 'String', 'CONSUMER_KEY', '"P22CdGN8TX2KPlIbUjc0Jhtwth8FFvH7"'
          it.buildConfigField 'String', 'CONSUMER_SECRET', '"lLXBDDL6HdmpoIvk"'
          it.buildConfigField 'String', 'BUSINESS_SHORT_CODE', '"174379"'
          it.buildConfigField 'String', 'PASSKEY', '"bfb279f9aa9bdbcf158e97dd71a467cd2e0c893059b10f78e6b72ada1ed2c919"'
          it.buildConfigField 'String', 'TRANSACTION_TYPE', '"CustomerPayBillOnline"'
          it.buildConfigField 'String', 'PARTYB', '"174379"'
          it.buildConfigField 'String', 'CALLBACK_URL', '"http://starkogi.com/mpesa_api_callback.php?acc_no="'
      }
  ```
    
Like this 

```
android {

   buildTypes.each {
        it.buildConfigField 'String', 'CONSUMER_KEY', '"P22CdGN8TX2KPlIbUjc0Jhtwth8FFvH7"'
        it.buildConfigField 'String', 'CONSUMER_SECRET', '"lLXBDDL6HdmpoIvk"'
        it.buildConfigField 'String', 'BUSINESS_SHORT_CODE', '"174379"'
        it.buildConfigField 'String', 'PASSKEY', '"bfb279f9aa9bdbcf158e97dd71a467cd2e0c893059b10f78e6b72ada1ed2c919"'
        it.buildConfigField 'String', 'TRANSACTION_TYPE', '"CustomerPayBillOnline"'
        it.buildConfigField 'String', 'PARTYB', '"174379"'
        it.buildConfigField 'String', 'CALLBACK_URL', '"http://starkogi.com/mpesa_api_callback.php?acc_no="'
    }
}
```

3. Add this line below to your build.gradle (Module: app) under dependencies
 
 ```
implementation 'com.github.starkogi:Mpesa-Payment-Library:VersionCode'

```

Like this

```

  dependencies {
        ...................
	        implementation 'com.github.starkogi:Mpesa-Payment-Library:v1.0.3'
   }

    
 ```
 
Your app module build.gradle should now look like this

```
apply plugin: 'com.android.application'

android {
    compileSdkVersion 28
    defaultConfig {
        applicationId "com.starkogi.myapplication"
        minSdkVersion 14
        targetSdkVersion 28
        versionCode 1
        versionName "1.0"
        testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
    }

    buildTypes.each {
        it.buildConfigField 'String', 'CONSUMER_KEY', '"P22CdGN8TX2KPlIbUjc0Jhtwth8FFvH7"'
        it.buildConfigField 'String', 'CONSUMER_SECRET', '"lLXBDDL6HdmpoIvk"'
        it.buildConfigField 'String', 'BUSINESS_SHORT_CODE', '"174379"'
        it.buildConfigField 'String', 'PASSKEY', '"bfb279f9aa9bdbcf158e97dd71a467cd2e0c893059b10f78e6b72ada1ed2c919"'
        it.buildConfigField 'String', 'TRANSACTION_TYPE', '"CustomerPayBillOnline"'
        it.buildConfigField 'String', 'PARTYB', '"174379"'
        it.buildConfigField 'String', 'CALLBACK_URL', '"http://starkogi.com/mpesa_api_callback.php?acc_no="'
    }
}

dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation 'com.android.support:appcompat-v7:28.0.0'
    implementation 'com.android.support.constraint:constraint-layout:1.1.3'
    testImplementation 'junit:junit:4.12'
    androidTestImplementation 'com.android.support.test:runner:1.0.2'
    androidTestImplementation 'com.android.support.test.espresso:espresso-core:3.0.2'

    implementation 'com.github.starkogi:Mpesa-Payment-Library:v1.0.2'

}
```

Your project module build.gradle should now look like this


```
// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:3.2.1'
        

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven { url 'https://jitpack.io' }

    }
}

task clean(type: Delete) {
    delete rootProject.buildDir
}

```

# Usage (Generate Mpesa STK Push)
You are now good to go...

Just Create STK Push Request Body from STKPushData.class

```

STKPushData stkPushData = new STKPushData(
                BuildConfig.BUSINESS_SHORT_CODE,
                BuildConfig.TRANSACTION_TYPE,
                "100", //Amount
                "254708374149", //Phone Number
                BuildConfig.CALLBACK_URL,
                "StarKogi", //Account Reference
                "Paying For Violets pedicure" //Transaction Desc
        );

```
Then make HTTP Request to safaricom api server.

NOTE : Change the last parameter value to true when going live
   
```   
new RequestClient(BuildConfig.CONSUMER_KEY, BuildConfig.CONSUMER_SECRET, BuildConfig.PASSKEY,
                false).createPushSTK(stkPushData);
```
Voilà you are good.

You'll receive a push on the phone registered on the STKPushData



