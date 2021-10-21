package com.example.projekt1_smb2

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Shop::class], version = 2)
abstract class ShopDB : RoomDatabase() {

    abstract fun shopDao(): ShopDao
    
    companion object{
        private var instance: ShopDB? = null
        
        fun getDatabase(context: Context): ShopDB{
            val tmpInstance = instance
            if(tmpInstance != null)
                return tmpInstance
            val inst = Room.databaseBuilder(context.applicationContext, ShopDB::class.java, "shopDB")
                    .allowMainThreadQueries().build()
            instance = inst
            return instance as ShopDB
        }
    }
}