package com.example.shoppingapp.room

import androidx.room.Embedded
import androidx.room.Relation

data class ShopWithGroceryItems (
    @Embedded val groceryListItems : GroceryListItems,
            @Relation(
                parentColumn = "id",
                entityColumn = "shopOwnerId"
            )
            val groveceryList: List<GroceryItems>
)