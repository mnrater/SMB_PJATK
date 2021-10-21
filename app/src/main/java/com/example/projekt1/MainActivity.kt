package com.example.projekt1

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.ListPreference
import androidx.preference.PreferenceManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sp = PreferenceManager.getDefaultSharedPreferences(this)
        val nightMode = sp.getBoolean("sw1", false)
        if (nightMode)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        else
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

    fun bt1Click(view: View) {
        val intent = Intent(this, ProductListActivity::class.java)
        startActivity(intent)
    }

//    fun bt2Click(view: View) {
//        val intent = Intent(this, SettingsActivity::class.java)
//        startActivity(intent)
//    }
    fun click(view: View) {
        val intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
    }
//    val user = mAuth.currentUser
//    if(user != null){
//        val name: String? = user.displayName
//        val email: String? = user.email
//        val photoUrl: Uri? = user.photoUrl
//        val verifiedEmail: Boolean = user.isEmailVerified
//        val uid: String = user.uid
//        val tokenResult = user.getIdToken(false)
//    }

    }

