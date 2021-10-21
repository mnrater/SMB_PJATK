package com.example.projekt1

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tProduct")
data class Product(var name: String, var price:Double, var quantity: Int, var isBought: Boolean ) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

}