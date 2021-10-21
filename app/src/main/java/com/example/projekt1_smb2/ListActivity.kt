package com.example.projekt1_smb2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projekt1_smb2.databinding.ActivityListBinding
import kotlinx.android.synthetic.main.activity_list.*

class ListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rv1.layoutManager = LinearLayoutManager(baseContext)
        binding.rv1.addItemDecoration(DividerItemDecoration(baseContext, DividerItemDecoration.VERTICAL))
        val productViewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(ProductViewModel::class.java)
        productViewModel.allProducts.observe(this, Observer{ products ->
            products?.let{
                (binding.rv1.adapter as ProductsAdapter).setProducts(it)
            }
        })
        binding.bt5.setOnClickListener() {
            productViewModel.insert(Product(et1.text.toString(), et2.text.toString(), et3.text.toString(), false))
            et1.text.clear()
            et2.text.clear()
            et3.text.clear()
            true
        }
//        productViewModel.insert(Product("Szafka", "2500", "10", true))
//        productViewModel.insert(Product("Komoda", "2100", "20", false))
        binding.rv1.adapter = ProductsAdapter(productViewModel)
    }
}