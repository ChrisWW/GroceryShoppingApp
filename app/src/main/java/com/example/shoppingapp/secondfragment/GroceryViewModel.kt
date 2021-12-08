package com.example.shoppingapp.secondfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoppingapp.GroceryRepository
import com.example.shoppingapp.room.GroceryItems
import com.example.shoppingapp.room.ShopWithGroceryItems
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class GroceryViewModel(private val repository: GroceryRepository) : ViewModel() {

//    private val _realAllData = MutableLiveData<List<ShopWithGroceryItems>>()
//    var realALlData: LiveData<List<ShopWithGroceryItems>> = _realAllData

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

    // do secondFragment
    fun getGroceriesWithProducts() = repository.getGroceriesWithProducts()

    fun getByIdGroceriesWithProducts(id: Int) : LiveData<ShopWithGroceryItems> = repository.getByIdGroceriesWithProducts(id)

    fun deleteByIdGroceriesWithProducts(id: List<Int>) = repository.deleteByIdGroceriesWithProducts(id)
//    {
//        GlobalScope.launch {
//            _realAllData.postValue(repository.getGroceriesWithProducts(groceryId))
//            repository.getGroceriesWithProducts()
//        }
//    }


//    fun getGroceriesWithProducts() = repository.getGroceriesWithProducts()
}