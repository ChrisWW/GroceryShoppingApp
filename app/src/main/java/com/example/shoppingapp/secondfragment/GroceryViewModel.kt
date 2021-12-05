package com.example.shoppingapp.secondfragment

import androidx.lifecycle.ViewModel
import com.example.shoppingapp.GroceryRepository
import com.example.shoppingapp.room.GroceryItems
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class GroceryViewModel(private val repository: GroceryRepository) : ViewModel() {

    fun insert(items: GroceryItems) = GlobalScope.launch {
        repository.insert(items)
    }

    fun delete(items: GroceryItems) = GlobalScope.launch {
        repository.delete(items)
    }

    fun getAllGroceryItems() = repository.getAllItems()


    fun insertDescription(items: List<GroceryItems>) = GlobalScope.launch {
        repository.insertDescription(items)
    }

    fun deleteDescription(items: List<GroceryItems>) = GlobalScope.launch {
        repository.deleteDescription(items)
    }

    fun Description() = repository.getAllItems()
}