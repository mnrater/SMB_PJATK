package com.example.projekt1_smb2

import android.content.ComponentName
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projekt1_smb2.databinding.ActivityListBinding
import kotlinx.android.synthetic.main.activity_list.*

class AddToListActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rv1.layoutManager = LinearLayoutManager(baseContext)
        binding.rv1.addItemDecoration(DividerItemDecoration(baseContext, DividerItemDecoration.VERTICAL))
        val shopViewModel = ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(ShopViewModel::class.java)
        shopViewModel.allShops.observe(this, Observer{ shops ->
            shops?.let{
                (binding.rv1.adapter as ShopsAdapter).setShops(it)
            }
        })
        binding.rv1.adapter = ShopsAdapter(shopViewModel)
    }
}