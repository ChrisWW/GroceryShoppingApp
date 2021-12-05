package com.example.shoppingapp.mainfragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingapp.R
import com.example.shoppingapp.room.GroceryItems
import com.example.shoppingapp.room.GroceryListItems
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import com.example.shoppingapp.MyNavDirections
import com.example.shoppingapp.secondfragment.ItemSecondFragment


class GroceryRvAdapterMain(
    var list: List<GroceryListItems>,
    val groceryItemClickInterface: GroceryItemClickInterface,
    private val vm: GroceryViewModelMain,
    val mComunicator: FragmentCommunication
//    private val onItemRootClicked: (Int) -> Unit
) : RecyclerView.Adapter<GroceryRvAdapterMain.GroceryViewHolderMain>() {


    inner class GroceryViewHolderMain(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTv = itemView.findViewById<TextView>(R.id.idTvItemNameMain)
        val deleteTv = itemView.findViewById<ImageView>(R.id.idTvDeleteMain)
        val rootView = itemView.rootView
    }

    interface GroceryItemClickInterface {
        fun onItemClick(groceryListItems: GroceryListItems)
    }

    interface FragmentCommunication {
        fun respond(position: Int, name: String?)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroceryViewHolderMain {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.grocery_rv_items_main, parent, false)
        return GroceryViewHolderMain(view)
    }

    override fun onBindViewHolder(holder: GroceryViewHolderMain, position: Int) {
        holder.nameTv.text = list.get(position).itemName
        holder.deleteTv.setOnClickListener {
            groceryItemClickInterface.onItemClick(list.get(position))
            vm.deleteList(list[position])
        }
//        val fragmentSecond = ItemSecondFragment()
//        val bundle = Bundle()
//        bundle.putString("ID", position.toString())
//        bundle.putString("NAME", list.get(position).itemName)
//        fragmentSecond.setArguments(bundle)

        holder.rootView.setOnClickListener {
            mComunicator.respond(position, list.get(position).itemName)


            val bundle = bundleOf("position" to position.toString(), "name" to list.get(position).itemName)
            holder.rootView.findNavController()
                .navigate(R.id.action_mainFragment_to_itemSecondFragment, bundle)


//            val action = MyNavDirections.actionGlobalMainFragment(position, list.get(position).itemName)
//            view?.findNavController().navigate(action)

//            Navigation.findNavController(holder.itemView)
//                .navigate(R.id.action_mainFragment_to_itemSecondFragment)
//        }


        }
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    override fun getItemCount(): Int {
        return list.size
    }
}