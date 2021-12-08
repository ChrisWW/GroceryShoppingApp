package com.example.shoppingapp.secondfragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton


import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoppingapp.GroceryRepository
import com.example.shoppingapp.databinding.FragmentItemSecondBinding
import com.example.shoppingapp.mainfragment.GroceryRvAdapterMain
import com.example.shoppingapp.room.GroceryDataBase
import com.example.shoppingapp.room.GroceryItems
import android.R
import androidx.appcompat.widget.Toolbar
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.shoppingapp.databinding.ActivityMainBinding
import com.example.shoppingapp.databinding.ToolbarBinding
import com.example.shoppingapp.room.ShopWithGroceryItems


class ItemSecondFragment() : Fragment(), GroceryRvAdapter.GroceryItemClickInterface{
    lateinit var itemsRV: RecyclerView
    lateinit var addFAB: FloatingActionButton
    var listGrocery: List<ShopWithGroceryItems> = emptyList()
    var list: List<GroceryItems> = emptyList()
    lateinit var groceryRvAdapter: GroceryRvAdapter
    lateinit var groceryViewModel: GroceryViewModel
    lateinit var binding: FragmentItemSecondBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        var binding = FragmentItemSecondBinding.bind(requireView())
//        val fragmentSecondBinding = binding
//
//        binding.toolbar

//        binding = FragmentItemSecondBinding.setContentView(this, R.layout.fragment_item_second)
//        setSupportActionBar(binding.toolbar)
//        binding.setProduct(product);

        binding = FragmentItemSecondBinding.inflate(layoutInflater)
//        val toolbarBinding = ToolbarBinding.bind(binding.toolbar)
//
//        (activity as AppCompatActivity?)!!.setSupportActionBar(toolbarBinding)


//        binding = FragmentItemSecondBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val toolbar = binding.toolbar
//        setSupportActionBar(toolbar)
//        val tv = view.findViewById<TextView>(R.id.textVAmount)


//        Log.d("second","FROM second fragment $position, $name")
//
//        val positionDialog = arguments?.putString("position_key",position)
//
//        val nameDialog = arguments?.putString("name_key", name)

//        Log.d("second","FROM second fragment DIALOG POSITION $positionDialog, $nameDialog")

        val groceryRepository = GroceryRepository(GroceryDataBase(requireContext()))
        val factory = GroceryViewModelFactory(groceryRepository)
        groceryViewModel = ViewModelProvider(this, factory).get(GroceryViewModel::class.java)

        groceryRvAdapter = GroceryRvAdapter(listGrocery ,list, this, groceryViewModel)
        binding.idRvItems.layoutManager = LinearLayoutManager(context)
        binding.idRvItems.adapter = groceryRvAdapter

        val position = arguments?.getString("position")
        val name = arguments?.getString("name")

        Log.d("null","FROM second fragment $position, $name")

        positionValue = position!!
        nameValue = name!!

        binding.textViewSecond.text = nameValue
        Log.d("second","FROM second fragment $positionValue, $nameValue")

//        if(positionValue.toInt() == 0){
//            positionValue = "1"
//        }
        groceryViewModel.getByIdGroceriesWithProducts(positionValue.toInt()).observe(viewLifecycleOwner, Observer {

            Log.d("TEST","${positionValue.toInt()}")
            Log.d("TEST","${listGrocery}")
            Log.d("TEST","${groceryRvAdapter.listGrocery}")
            Log.d("TEST","${it}")
//            Log.d("TEST","${it.groveceryList}")
            if(it == null || it.groveceryList.isEmpty()) {
                it?.groveceryList = emptyList()
                groceryRvAdapter.list = emptyList()
            }else {
            groceryRvAdapter.list = it.groveceryList
            }
            groceryRvAdapter.notifyDataSetChanged()
        })
//        groceryViewModel.getAllGroceryItems().observe(viewLifecycleOwner, Observer {
//            groceryRvAdapter.listGrocery = groceryViewModel.getByIdGroceriesWithProducts(position.toInt())
////            groceryRvAdapter.list = it
//            Log.d("secondrfragment", "${groceryRvAdapter.listGrocery}")
//            groceryRvAdapter.notifyDataSetChanged()
//        })

        binding.idFABadd.setOnClickListener {
//            val bundle = bundleOf("position" to position.toString(), "name" to name)
////            findNavController().navigate(com.example.shoppingapp.R.id.action_itemSecondFragment_to_my_dialog, bundle)

            openDialog()
        }
        binding.deleteAll.setOnClickListener {
            groceryViewModel.deleteByIdGroceriesWithProducts(groceryRvAdapter.getProducts().map { it.id })
        }
    }

    fun openDialog() {
        AddRangeDialog(requireContext(), object: GroceryRvAdapter.GroceryItemClickInterface {
            override fun onItemClick(groceryItems: GroceryItems) {
                groceryViewModel.insert(groceryItems)
            }
        }).show()

    }

    override fun onItemClick(groceryItems: GroceryItems) {
        groceryViewModel.delete(groceryItems)
        groceryRvAdapter.notifyDataSetChanged()
        Toast.makeText(context, "Item deleted...", Toast.LENGTH_SHORT)
    }
    companion object {
        var positionValue = ""
        var nameValue = ""
    }


}
