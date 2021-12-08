package com.example.shoppingapp.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "grocery_category")
data class GroceryListItems(
    @ColumnInfo(name = "itemName")
    var itemName: String
    ) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}