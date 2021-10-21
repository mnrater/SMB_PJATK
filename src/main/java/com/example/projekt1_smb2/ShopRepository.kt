package com.example.projekt1_smb2

import androidx.lifecycle.LiveData

class ShopRepository(private val shopDao: ShopDao) {

    val allShops: LiveData<List<Shop>> = shopDao.getShops()

    fun getShopId (id: Long): Shop{
        return shopDao.getShopId(id)
    }

    fun insert(shop: Shop): Long{
        return shopDao.insert(shop)
    }

    fun delete(shop: Shop){
        shopDao.delete(shop)
    }

    fun update(shop: Shop){
        shopDao.update(shop)
    }
}