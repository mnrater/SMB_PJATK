package com.example.projekt1

import android.app.Person
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ProductDao {

    @Query("SELECT * FROM tProduct")
    fun getProducts(): LiveData<List<Product>>

    @Insert
    fun addProduct(product: Product):Long

    @Delete
    fun delProduct(product: Product)

    @Update
    fun updateProduct(product: Product)

    @Query("SELECT * FROM tProduct WHERE id = :id")
    fun getProductById(id: Long): Product

}