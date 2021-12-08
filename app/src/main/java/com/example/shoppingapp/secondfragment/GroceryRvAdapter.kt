package com.example.shoppingapp.secondfragment

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingapp.R
import com.example.shoppingapp.room.GroceryItems
import com.example.shoppingapp.room.GroceryListItems
import com.example.shoppingapp.room.ShopWithGroceryItems

class GroceryRvAdapter (var listGrocery: List<ShopWithGroceryItems> ,var list: List<GroceryItems>, val groceryItemClickInterface: GroceryItemClickInterface, private val vm: GroceryViewModel) : RecyclerView.Adapter<GroceryRvAdapter.GroceryViewHolder>(){



inner class GroceryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val nameTv = itemView.findViewById<TextView>(R.id.idTvItemName)
        val quantityTv = itemView.findViewById<TextView>(R.id.idTvQuantity)
        val rateTv = itemView.findViewById<TextView>(R.id.idTvRate)
        val amounTv = itemView.findViewById<TextView>(R.id.idTvAmount)
        val deleteTv = itemView.findViewById<ImageView>(R.id.idTvDelete)

    }

    interface GroceryItemClickInterface {
        fun onItemClick(groceryItems: GroceryItems)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroceryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.grocery_rv_items, parent, false)
        return GroceryViewHolder(view)
    }

    fun getProducts() : List<GroceryItems> {
        return list
    }
    override fun onBindViewHolder(holder: GroceryViewHolder, position: Int) {
//        val flattenedList: List<GroceryItems> = list.flatten()
//        holder.nameTv.text = (listGrocery.get(1)).groveceryList.get(position).itemName
//        holder.quantityTv.text = listGrocery.get(1).groveceryList.get(position).itemQuantity.toString()
//        holder.rateTv.text = listGrocery.get(position).groveceryList.get(position).toString()
//        val itemTotal : Int = listGrocery.get(1).groveceryList.get(position).itemPrice*listGrocery.get(1).groveceryList.get(position).itemQuantity
//        holder.amounTv.text = "$ " + itemTotal.toString()

        holder.nameTv.text = list.get(position).itemName
        holder.quantityTv.text = list.get(position).itemQuantity.toString()
        holder.rateTv.text = list.get(position).itemPrice.toString()
        val itemTotal : Int = list.get(position).itemPrice*list.get(position).itemQuantity
        holder.amounTv.text = "$ " + itemTotal.toString()


        holder.deleteTv.setOnClickListener {
//            groceryItemClickInterface.onItemClick(list.get(position))
            vm.delete(list[position])
        }

        }

    override fun getItemCount(): Int {
        Log.d("size", "Items: ${ list.size}")
        Log.d("size", "ShopsWithItems: ${listGrocery.size}")
//        Log.d("size", "ShopsWithItems: ${listGrocery.get(0).groveceryList.size}")
        //    java.lang.IndexOutOfBoundsException: Empty list doesn't contain element at index 0.

        return list.size
    }
}