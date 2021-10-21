package com.example.projekt1

import android.app.Application
import android.app.Person
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class ProductViewModel(app: Application) : AndroidViewModel(app){

    private val repo: ProductRepository
    val allProducts: LiveData<List<Product>>

    init{
        val productDao: ProductDao = ProductDB.getDataBase(app).productDao()
        repo = ProductRepository(productDao)
        allProducts=repo.allProduct
    }

    fun insert (product: Product) :Long {
        return repo.insert(product)
    }
    fun delete (product: Product) = repo.delete(product)
    fun update (product: Product) = repo.update(product)
    fun getProductById (id: Long) :Product {
        return repo.getProductById(id)
    }

}