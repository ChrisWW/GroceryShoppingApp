package com.example.shoppingapp

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import androidx.fragment.app.DialogFragment
import com.example.shoppingapp.databinding.AddRangeDialogBinding
import com.example.shoppingapp.databinding.FragmentItemSecondBinding


class AddRangeDialog(context: Context, var groceryItemClickInterface: GroceryRvAdapter.GroceryItemClickInterface) : AppCompatDialog(context) {

    lateinit var binding: AddRangeDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_range_dialog)
        binding = AddRangeDialogBinding.inflate(layoutInflater)

        binding.addButton.setOnClickListener {
            val itemName = binding.itemName.text.toString()
            val itemQuantity = binding.itemQuantity.text.toString()
            val itemPrice = binding.itemPrice.text.toString()

            if(itemName.isEmpty() || itemQuantity.isEmpty() || itemPrice.isEmpty()) {
                Toast.makeText(context, "Please enter all the information", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val item = GroceryItems(itemName, itemQuantity.toInt(), itemPrice.toInt())
            groceryItemClickInterface.onItemClick(item)
            dismiss()
            Log.d("dialog", "Add Dialog")
        }

        binding.cancelButton.setOnClickListener {
            cancel()
            Log.d("dialog", "Cancel Dialog")
        }
    }
}


//class AddRangeDialog(val addRemoveFunc: (Int, Int, Boolean) -> (Unit)) : DialogFragment() {
//    lateinit var groceryViewModel: GroceryViewModel
//    lateinit var groceryRvAdapter: GroceryRvAdapter
//
//    private fun doInputCheck(start: Int, end: Int): Boolean {
//        if (start < 1 || end < 1) {
//            Toast.makeText(
//                context,
//                context?.getText(R.string.no_negative_numbers),
//                Toast.LENGTH_LONG
//            ).show()
//            return false
//        }
//        if (end < start) {
//            Toast.makeText(
//                context,
//                context?.getText(R.string.end_number_bigger_than_start),
//                Toast.LENGTH_LONG
//            )
//                .show()
//            return false
//        }
//
//        return true
//
//    }
//
//
//    lateinit var binding: AddRangeDialogBinding
//
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        binding = AddRangeDialogBinding.inflate(layoutInflater)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        dialog?.setTitle(R.string.dialog_remove_add_title)
//
//
//        binding.addButton.setOnClickListener {
//            val itemName : String = binding.itemName.toString()
//            val itemPrice : String = binding.itemPrice.toString()
//            val itemQuantity: String = binding.itemQuantity.toString()
//
//            val qty : Int = itemQuantity.toInt()
//            val pr : Int = itemPrice.toInt()
//
//            if(itemName.isNotEmpty() && itemPrice.isNotEmpty() && itemQuantity.isNotEmpty()) {
//                val items = GroceryItems(itemName, qty, pr)
//                groceryViewModel.insert(items)
//                Toast.makeText(context, "Item innserted...", Toast.LENGTH_SHORT)
//                groceryRvAdapter.notifyDataSetChanged()
//                dialog?.dismiss()
//            } else {
//                Toast.makeText(context, "Change value...", Toast.LENGTH_SHORT)
//            }
//            dialog?.show()
//        }
//
//        binding.removeButton.setOnClickListener {
////            val itemName : String = binding.itemName.toString()
////            val itemName : String = binding.itemName.toString()
////            if (doInputCheck(start, end)) {
////                dismiss()
////                addRemoveFunc(start, end, false)
////            }
//        }
//
//        binding.cancelButton.setOnClickListener {
//            dismiss()
//        }
//
//    }
//}