package com.example.shoppingapp

import com.example.shoppingapp.room.GroceryDataBase
import com.example.shoppingapp.room.GroceryItems
import com.example.shoppingapp.room.GroceryListItems

class GroceryRepository(private val db: GroceryDataBase) {

    suspend fun insert(items: GroceryItems) = db.getGroceryDao().insert(items)
    suspend fun delete(items: GroceryItems) = db.getGroceryDao().delete(items)

    fun getAllItems() = db.getGroceryDao().getAllGroceryItems()

    suspend fun insertDescription(items: List<GroceryItems>) = db.getGroceryDao().insertDescription(items)
    suspend fun deleteDescription(items: List<GroceryItems>) = db.getGroceryDao().deleteDescription(items)

    fun getAllItemsDescription() = db.getGroceryDao().getAllGroceryItems()

    suspend fun insertList(items: GroceryListItems) = db.getGroceryDao().insertList(items)
    suspend fun deleteList(items: GroceryListItems) = db.getGroceryDao().deleteList(items)

    fun getAllItemsList() = db.getGroceryDao().getAllGroceryItemsList()

}