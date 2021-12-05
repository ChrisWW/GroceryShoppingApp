package com.example.shoppingapp.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface GroceryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: GroceryItems)

    @Delete
    suspend fun delete(item: GroceryItems)

    @Query("DELETE FROM grocery_items WHERE id = :itemId")
    suspend fun deleteItem(itemId: Int)

    @Query("SELECT * FROM grocery_items")
    fun getAllGroceryItems() : LiveData<List<GroceryItems>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDescription(item: List<GroceryItems>)

    @Delete
    suspend fun deleteDescription(item: List<GroceryItems>)

    @Query("SELECT * FROM grocery_items")
    fun getAllGroceryItemsDescription() : LiveData<List<GroceryItems>>


    //
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertList(item: GroceryListItems)

    @Delete
    suspend fun deleteList(item: GroceryListItems)

    @Query("DELETE FROM grocery_items_list WHERE id = :itemId")
    suspend fun deleteItemList(itemId: Int)

    @Query("SELECT * FROM grocery_items_list")
    fun getAllGroceryItemsList() : LiveData<List<GroceryListItems>>
}
