package com.example.app_bs_service

import android.content.*
import android.app.PendingIntent
import android.content.*
import android.os.Build
import android.os.IBinder
import android.widget.Toast

class ProductReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        val newIntent = Intent (context.applicationContext, ProductService::class.java)
        newIntent.putExtras(intent)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(newIntent);
        } else {
            context.startService(newIntent);
        }
    }
}