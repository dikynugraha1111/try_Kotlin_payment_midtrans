package com.example.try_midtrans

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.midtrans.sdk.corekit.callback.TransactionFinishedCallback
import com.midtrans.sdk.corekit.core.themes.CustomColorTheme
import com.midtrans.sdk.corekit.models.snap.TransactionResult
import com.midtrans.sdk.uikit.SdkUIFlowBuilder

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        SdkUIFlowBuilder.init()
            .setClientKey("SB-Mid-client-njnJpVe5frGCthE0")
            .setContext(applicationContext)
            .setTransactionFinishedCallback(TransactionFinishedCallback {
                result ->
                //logic for transaction result
            })
            .setMerchantBaseUrl("") //Isi dengan file PHP (fork di git) dan telah di hosting di web. Cantumkan URL disini
            .enableLog(true)
            .setColorTheme(CustomColorTheme("#FFE51255", "#B61548", "#FFE51255"))
            .setLanguage("id")
            .buildSDK()
    }

}
