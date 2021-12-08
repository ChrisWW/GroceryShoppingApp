package com.example.shoppingapp.mainfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.example.shoppingapp.GroceryRepository
import com.example.shoppingapp.R
import com.example.shoppingapp.room.GroceryItems
import com.example.shoppingapp.room.GroceryListItems
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class GroceryViewModelMain(private val repository: GroceryRepository) : ViewModel() {

//    private val _openTaskEvent = MutableLiveData<Event<String>>()
//    val openTaskEvent: LiveData<Event<String>> = _openTaskEvent

    fun insertList(items: GroceryListItems) = GlobalScope.launch {
        repository.insertList(items)
    }

    fun deleteList(items: GroceryListItems) = GlobalScope.launch {
        repository.deleteList(items)
    }

    fun getAllList() = repository.getAllItemsList()

    //transaction
//    fun getGroceriesWithProducts() = repository.getGroceriesWithProducts()


    fun onItemRootClicked(id: Int) {

    }
}


//class Event<out T>(private val content: T) {
//
//    @Suppress("MemberVisibilityCanBePrivate")
//    var hasBeenHandled = false
//        private set // Allow external read but not write
//
//    /**
//     * Returns the content and prevents its use again.
//     */
//    fun getContentIfNotHandled(): T? {
//        return if (hasBeenHandled) {
//            null
//        } else {
//            hasBeenHandled = true
//            content
//        }
//    }
//
//    /**
//     * Returns the content, even if it's already been handled.
//     */
//    fun peekContent(): T = content
//}
//
///**
// *
// *
// *
// * An [Observer] for [Event]s, simplifying the pattern of checking if the [Event]'s content has
// * already been handled.
// *
// * [onEventUnhandledContent] is *only* called if the [Event]'s contents has not been handled.
// */
//class EventObserver<T>(private val onEventUnhandledContent: (T) -> Unit) : Observer<Event<T>> {
//    override fun onChanged(event: Event<T>?) {
//        event?.getContentIfNotHandled()?.let {
//            onEventUnhandledContent(it)
//        }
//    }
//}