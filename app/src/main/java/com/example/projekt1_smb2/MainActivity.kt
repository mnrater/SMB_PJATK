package com.example.projekt1_smb2

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

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
        val intent2 = Intent(baseContext, SettingsActivity::class.java)
        bt2.setOnClickListener{
            startActivity(intent2)}
        }
    }