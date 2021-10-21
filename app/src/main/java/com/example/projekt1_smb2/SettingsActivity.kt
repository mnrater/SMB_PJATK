package com.example.projekt1_smb2

import android.graphics.Color
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RadioButton
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {

    private lateinit var sp : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        sp = getSharedPreferences("prefs_file1", Context.MODE_PRIVATE)

    }

    override fun onStart() {
        super.onStart()
        val color = sp.getString("color1", "Black")
        if (color == "Blue"){
            tv_options.setTextColor(Color.BLUE)}
        if (color == "Black"){
            tv_options.setTextColor(Color.BLUE)
        }
        if (color == "Red"){
            tv_options.setTextColor(Color.RED)}
    }

    override fun onStop() {
        super.onStop()
        val selected_id = rg1.checkedRadioButtonId
        val editor = sp.edit()
        editor.putString("color1", findViewById<RadioButton>(selected_id).text.toString())
        editor.apply()
    }


    fun click(view: View) {
        val selected_id = rg1.checkedRadioButtonId
        val color = findViewById<RadioButton>(selected_id).text.toString()
        if (color == "Blue"){
            tv_options.setTextColor(Color.BLUE)}
        if (color == "Red"){
            tv_options.setTextColor(Color.RED)}
    }

}