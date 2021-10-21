package com.example.projekt1_smb2

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ShopDao {

    @Query("SELECT * FROM shop_table")
    fun getShops(): LiveData<List<Shop>>

    @Query("SELECT * FROM shop_table WHERE id = :id")
    fun getShopId(id: Long): Shop

    @Insert
    fun insert(shop: Shop): Long

    @Delete
    fun delete(shop: Shop)

    @Update
    fun update(shop: Shop)
}