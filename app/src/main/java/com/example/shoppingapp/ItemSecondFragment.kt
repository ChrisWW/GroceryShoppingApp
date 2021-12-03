package com.example.shoppingapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton


import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoppingapp.databinding.ActivityMainBinding.inflate
import com.example.shoppingapp.databinding.FragmentItemSecondBinding
import com.example.shoppingapp.databinding.FragmentItemSecondBinding.inflate

class ItemSecondFragment : Fragment(), GroceryRvAdapter.GroceryItemClickInterface {
    lateinit var itemsRV: RecyclerView
    lateinit var addFAB: FloatingActionButton
    var list: List<GroceryItems> = emptyList()
    lateinit var groceryRvAdapter: GroceryRvAdapter
    lateinit var groceryViewModel: GroceryViewModel
    lateinit var binding: FragmentItemSecondBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentItemSecondBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
