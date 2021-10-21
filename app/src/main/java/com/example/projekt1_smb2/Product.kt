package com.example.projekt1_smb2

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product_table")
data class Product(var name: String, var price: String, var quantity: String, var bought: Boolean) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}