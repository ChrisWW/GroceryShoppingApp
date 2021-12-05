package com.example.shoppingapp.mainfragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.shoppingapp.GroceryRepository

class GroceryViewModelFactoryMain(private val repository: GroceryRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T: ViewModel?> create(modelClass: Class<T>) : T {
        return GroceryViewModelMain(repository) as T
    }
}