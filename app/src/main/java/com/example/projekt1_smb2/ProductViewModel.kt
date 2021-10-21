package com.example.projekt1_smb2

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class ProductViewModel(app: Application): AndroidViewModel(app) {

    private val repo: ProductRepository
    val allProducts: LiveData<List<Product>>

    init {
        val productDao = ProductDB.getDatabase(app).productDao()
        repo = ProductRepository(productDao)
        allProducts = repo.allProducts
    }

    fun insert(product: Product) = repo.insert(product)
    fun delete(product: Product) = repo.delete(product)
    fun update(idd: Long, name: String, price: String, quantity: String) = repo.update(idd, name, price, quantity)

}