package com.example.projekt1


import android.content.ComponentName
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
//import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projekt1.databinding.ActivityProductListBinding
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_product_list.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


class ProductListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityProductListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val database = FirebaseDatabase.getInstance()
        val ref = database.getReference("Product")

        binding.rv1.layoutManager = LinearLayoutManager(baseContext)
        binding.rv1.addItemDecoration(DividerItemDecoration(baseContext, DividerItemDecoration.VERTICAL))

        var somelist: ArrayList<List<String>> = ArrayList(emptyList())
        binding.rv1.adapter = MyAdapter(this, somelist, ref)
        bt_add.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                val products = arrayListOf<String>()
                products.add(et_name.text.toString())
                products.add(et_amount.text.toString())
                products.add(et_price.text.toString())
                products.add("false")
                ref.push().setValue(products)

            }
        }
    }

}