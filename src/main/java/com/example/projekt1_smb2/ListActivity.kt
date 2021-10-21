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

class ListActivity : AppCompatActivity() {

//    private lateinit var receiver: ShopReceiver

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
//        val shop = Shop(et1.text.toString(), et2.text.toString(), et3.text.toString())
//        val intent8 = Intent(baseContext, MapsActivity::class.java)
//        binding.bt5.setOnClickListener() {
//            val name = et1.text.toString()
//            val description = et3.text.toString()
//            val radius = et2.text.toString()
//            val shop = Shop(name, description, radius)
//            val id = shopViewModel.insert(shop)
//            et1.text.clear()
//            et2.text.clear()
//            et3.text.clear()
//            intent8.putExtra("shop_id", id)

//            startActivity(intent8)
//        }
        binding.rv1.adapter = ShopsAdapter(shopViewModel)
    }
}