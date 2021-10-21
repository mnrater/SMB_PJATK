package com.example.projekt1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_product_edit.*
import kotlinx.android.synthetic.main.activity_product_list.*

class ProductEditActivity : AppCompatActivity() {

    private var productToEdit = Product("", 0.0, 0, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_edit)

        val id = intent.getLongExtra("product_id", 0)

        val productViewModel = ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(ProductViewModel::class.java)
        productToEdit = productViewModel.getProductById(id)

        editTextTextPersonName.setText(productToEdit.name)
        editTextTextPersonName2.setText(productToEdit.price.toString())
        editTextTextPersonName3.setText(productToEdit.quantity.toString())
    }

    fun buttonClicl(view: View) {

        try {
            productToEdit.name = editTextTextPersonName.text.toString()
            productToEdit.price = editTextTextPersonName2.text.toString().toDouble()
            productToEdit.quantity = editTextTextPersonName3.text.toString().toInt()
            productToEdit.isBought = false

            val productViewModel = ViewModelProvider(this,
                    ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(ProductViewModel::class.java)
            productViewModel.update(productToEdit)
        } catch (e: NumberFormatException) {
            Toast.makeText(this, "Incorrect values", Toast.LENGTH_LONG).show()
        }

        startActivity(Intent(this, ProductListActivity::class.java))
    }
}