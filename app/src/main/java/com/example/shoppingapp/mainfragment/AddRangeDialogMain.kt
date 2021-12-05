package com.example.shoppingapp.mainfragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import com.example.shoppingapp.R
import com.example.shoppingapp.room.GroceryItems
import com.example.shoppingapp.room.GroceryListItems


class AddRangeDialogMain(context: Context, var groceryItemClickInterface: GroceryRvAdapterMain.GroceryItemClickInterface) : AppCompatDialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_range_dialog_main)
        val addButton = findViewById<Button>(R.id.addButtonMain)
        val cancelButton = findViewById<Button>(R.id.cancelButtonMain)
        val itemNameText = findViewById<EditText>(R.id.itemNameMain)

            addButton?.setOnClickListener {
            val itemName = itemNameText?.text.toString()

            if(itemName.isEmpty()) {
                Toast.makeText(context, "Please enter all the information", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val item = GroceryListItems(itemName)
            groceryItemClickInterface.onItemClick(item)
            dismiss()
            Log.d("dialog", "Add Dialog")
        }

        cancelButton?.setOnClickListener {
            cancel()
            Log.d("dialog", "Cancel Dialog")
        }
    }
}
