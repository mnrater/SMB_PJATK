package com.example.projekt1

import androidx.lifecycle.LiveData

class ProductRepository(private val productDao: ProductDao) {

    val allProduct: LiveData<List<Product>> = productDao.getProducts()

    fun insert(product: Product):Long{
        return productDao.addProduct(product)
    }
    fun delete (product: Product){
        productDao.delProduct(product)
    }
    fun update (product: Product){
        productDao.updateProduct(product)
    }
    fun getProductById (id: Long): Product{
       return productDao.getProductById(id)
    }
    fun insertFIREBASE(product: Product):Long{
        return productDao.addProduct(product)
    }

}