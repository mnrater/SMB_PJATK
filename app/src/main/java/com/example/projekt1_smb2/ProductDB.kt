package com.example.projekt1_smb2

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Product::class], version = 2)
abstract class ProductDB : RoomDatabase() {

    abstract fun productDao(): ProductDao
    
    companion object{
        private var instance: ProductDB? = null
        
        fun getDatabase(context: Context): ProductDB{
            val tmpInstance = instance
            if(tmpInstance != null)
                return tmpInstance
            val inst = Room.databaseBuilder(context.applicationContext, ProductDB::class.java, "productDB")
                .allowMainThreadQueries().build()
            instance = inst
            return instance as ProductDB
        }
    }
}