package com.example.app_bs_service

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.*
import android.os.Build
import android.os.IBinder
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationManagerCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            createChannel()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun createChannel(){
        val notificationChannel = NotificationChannel(
                "productAddChannel",
                "Product channel",
                NotificationManager.IMPORTANCE_DEFAULT
        )
        NotificationManagerCompat.from(this)
                .createNotificationChannel(notificationChannel)
    }
}