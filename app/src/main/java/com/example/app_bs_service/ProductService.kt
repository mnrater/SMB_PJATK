package com.example.app_bs_service

import android.app.PendingIntent
import android.app.Service
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class ProductService : Service() {

    private var mBinder: MyBinder

    init{
        mBinder = MyBinder()
    }

    inner class MyBinder : Binder(){
        fun getService(): ProductService = this@ProductService
    }
    override fun onBind(intent: Intent): IBinder {
        return mBinder
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        if (intent != null) {
            val listIntent = Intent()
            listIntent.component = ComponentName("com.example.projekt1_smb2",
                    "com.example.projekt1_smb2.EditActivity")
            val productId= intent.getLongExtra("product_id", 0)
            listIntent.putExtra("product_id", productId)

            val pendingIntent = PendingIntent.getActivity(
                    this,
                    intent.getLongExtra("product_id", 0).toInt(),
                    listIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT
            )
            var channel = intent.getStringExtra("channel")
            if (channel == null)
                channel = "default"
            val notification = NotificationCompat.Builder(this, channel)
                    .setSmallIcon(R.mipmap.ic_launcher_round)
                    .setContentTitle("Dodano element. Kliknij, aby edytować!")
                    .setContentText(intent.getStringExtra("name"))
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true)
                    .build()

            NotificationManagerCompat.from(this).notify(
                    intent.getLongExtra("product_id", 0).toInt(),
                    notification
            )
        }
        return super.onStartCommand(intent, flags, startId)
    }
}