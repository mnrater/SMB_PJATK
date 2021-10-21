package com.example.projekt1_smb2

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class ShopViewModel(app: Application): AndroidViewModel(app) {

    private val repo: ShopRepository
    val allShops: LiveData<List<Shop>>

    init {
        val shopDao = ShopDB.getDatabase(app).shopDao()
        repo = ShopRepository(shopDao)
        allShops = repo.allShops
    }
    fun insert(shop: Shop): Long {
        return repo.insert(shop)
    }
    fun delete(shop: Shop) {
            repo.delete(shop)
    }
    fun update(shop: Shop) {
            repo.update(shop)
    }
    fun getShopId (id: Long): Shop {
        return repo.getShopId(id)
    }
}