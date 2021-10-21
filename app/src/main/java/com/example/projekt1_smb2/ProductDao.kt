package com.example.projekt1_smb2

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ProductDao {

    @Query("SELECT * FROM product_table")
    fun getProducts(): LiveData<List<Product>>

    @Insert
    fun insert(product: Product)

    @Delete
    fun delete(product: Product)

    @Query("UPDATE product_table SET name = :name, quantity = :quantity, price = :price WHERE product_table.id = :idd")
    fun update(idd: Long, name: String, price: String, quantity: String)
}