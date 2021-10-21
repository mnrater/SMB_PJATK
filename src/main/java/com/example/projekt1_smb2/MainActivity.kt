package com.example.projekt1_smb2

import android.Manifest
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.content.pm.PackageManager
import android.content.*
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.projekt1_smb2.MediaPlayerService.LocalBinder
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var player: MediaPlayerService
    var serviceBound = false

    private lateinit var sp: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sp = getPreferences(Context.MODE_PRIVATE)
        val intent1 = Intent(baseContext, ListActivity::class.java)
        bt1.setOnClickListener {
            startActivity(intent1)
        }
        val intent2 = Intent(baseContext, MapsActivity::class.java)
        bt2.setOnClickListener{
            startActivity(intent2)}
        
        val playerIntent = Intent(this, MediaPlayerService::class.java)
        startService(playerIntent)
        }
    }