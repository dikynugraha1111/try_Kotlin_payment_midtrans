package com.example.try_midtrans

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.midtrans.sdk.corekit.callback.TransactionFinishedCallback
import com.midtrans.sdk.corekit.core.MidtransSDK
import com.midtrans.sdk.corekit.core.TransactionRequest
import com.midtrans.sdk.corekit.core.themes.CustomColorTheme
import com.midtrans.sdk.corekit.models.BillingAddress
import com.midtrans.sdk.corekit.models.CustomerDetails
import com.midtrans.sdk.corekit.models.ItemDetails
import com.midtrans.sdk.corekit.models.ShippingAddress
import com.midtrans.sdk.corekit.models.snap.TransactionResult
import com.midtrans.sdk.uikit.SdkUIFlowBuilder
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        SdkUIFlowBuilder.init()
            .setClientKey("SB-Mid-client-njnJpVe5frGCthE0")
            .setContext(applicationContext)
            .setTransactionFinishedCallback(TransactionFinishedCallback {
                //logic for transaction result
            })
            .setMerchantBaseUrl("https://flutter-midtrans.herokuapp.com/index.php/") //Isi dengan file PHP (fork di git) dan telah di hosting di web. Cantumkan URL disini
            .enableLog(true)
            .setColorTheme(CustomColorTheme("#FFE51255", "#B61548", "#FFE51255"))
            .setLanguage("id")
            .buildSDK()

        pesan.setOnClickListener {
            //Declare Variable's
            val harga = 20000
            val quantity = jumlah.text.toString()
            val total = harga.toDouble() * quantity.toDouble()
            val catatan = catatan.text.toString()
            //Create Transaction Request
            val transactionRequest = TransactionRequest("Diky-Store"+System.currentTimeMillis().toShort()+"",total)
            //Create Item Detail's
            val item = ItemDetails("NamaItemId",harga.toDouble(),quantity.toInt(),"KOTLIN")
            val itemDetails = ArrayList<ItemDetails>()
            itemDetails.add(item)
            //Create Customer Detail Object
            uiKitsDetails(transactionRequest)
            MidtransSDK.getInstance().transactionRequest = transactionRequest
            MidtransSDK.getInstance().startPaymentUiFlow(this)

        }
    }

    fun uiKitsDetails(transactionRequest: TransactionRequest){
        //Masih menggunakan metode statis value, kalau sudah masuk deploy program gunakan
        //metode dinamis, dengan memasukan value ke dalam parameter function
        val customerDetails = CustomerDetails()
        customerDetails.customerIdentifier = "DikyNugraha"
        customerDetails.phone = "08977901651"
        customerDetails.firstName = "Diky"
        customerDetails.lastName = "Nugraha"
        customerDetails.email = "dikynugraha1111@gmail.com"

        val shippingAddress = ShippingAddress()
        shippingAddress.address = "Magetan,Jawa Timur"
        shippingAddress.city = "Magetan"
        shippingAddress.postalCode = "63319"
        customerDetails.shippingAddress = shippingAddress

        val billingAddress = BillingAddress()
        billingAddress.address = "Magetan,Jawa Timur"
        billingAddress.city = "Magetan"
        billingAddress.postalCode = "63319"
        customerDetails.billingAddress = billingAddress

        transactionRequest.customerDetails = customerDetails
    }

}
