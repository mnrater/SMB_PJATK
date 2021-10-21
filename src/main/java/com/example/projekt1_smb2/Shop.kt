package com.example.projekt1_smb2

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shop_table")
data class Shop(var name: String, var description: String, var radius: String) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}