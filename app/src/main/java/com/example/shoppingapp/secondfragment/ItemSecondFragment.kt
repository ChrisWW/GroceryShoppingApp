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
import com.example.shoppingapp.databinding.ActivityMainBinding
import com.example.shoppingapp.databinding.ToolbarBinding


class ItemSecondFragment : Fragment(), GroceryRvAdapter.GroceryItemClickInterface{
    lateinit var itemsRV: RecyclerView
    lateinit var addFAB: FloatingActionButton
    var list: List<GroceryItems> = emptyList()
    lateinit var groceryRvAdapter: GroceryRvAdapter
    lateinit var groceryViewModel: GroceryViewModel
    lateinit var binding: FragmentItemSecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
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



        val position = arguments?.getString("position")
        val name = arguments?.getString("name")

        Log.d("second","FROM second fragment $position, $name")

        val groceryRepository = GroceryRepository(GroceryDataBase(requireContext()))
        val factory = GroceryViewModelFactory(groceryRepository)
        groceryViewModel = ViewModelProvider(this, factory).get(GroceryViewModel::class.java)

        groceryRvAdapter = GroceryRvAdapter(list, this, groceryViewModel)
        binding.idRvItems.layoutManager = LinearLayoutManager(context)
        binding.idRvItems.adapter = groceryRvAdapter
        groceryViewModel.getAllGroceryItems().observe(viewLifecycleOwner, Observer {
            groceryRvAdapter.list = it
            groceryRvAdapter.notifyDataSetChanged()
        })

        binding.idFABadd.setOnClickListener {
            openDialog()
        }
        binding.deleteAll.setOnClickListener {

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
}
