package com.example.projekt1_smb2

import androidx.lifecycle.LiveData

class ProductRepository(private val productDao: ProductDao) {

    val allProducts: LiveData<List<Product>> = productDao.getProducts()

    fun insert(product: Product){
        productDao.insert(product)
        }

    fun delete(product: Product){
        productDao.delete(product)
    }

    fun update(idd: Long, name: String, price: String, quantity: String){
        productDao.update(idd, name, price, quantity)
    }
}