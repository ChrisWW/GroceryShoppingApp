package com.example.shoppingapp.mainfragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoppingapp.GroceryRepository
import com.example.shoppingapp.MyNavDirections
import com.example.shoppingapp.R
import com.example.shoppingapp.databinding.FragmentMainBinding
import com.example.shoppingapp.room.GroceryDataBase
import com.example.shoppingapp.room.GroceryItems
import com.example.shoppingapp.room.GroceryListItems
import com.example.shoppingapp.secondfragment.*

class MainFragment : Fragment(), GroceryRvAdapterMain.GroceryItemClickInterface, GroceryRvAdapterMain.FragmentCommunication {
    lateinit var binding: FragmentMainBinding
    var list: List<GroceryListItems> = emptyList()
    lateinit var groceryRvAdapterMain: GroceryRvAdapterMain
    lateinit var groceryViewModelMain: GroceryViewModelMain


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_itemSecondFragment)
        }

        val groceryRepository = GroceryRepository(GroceryDataBase(requireContext()))
        val factory = GroceryViewModelFactoryMain(groceryRepository)
        groceryViewModelMain = ViewModelProvider(this, factory).get(GroceryViewModelMain::class.java)
//        , groceryViewModelMain::onItemRootClicked
        groceryRvAdapterMain = GroceryRvAdapterMain(list, this, groceryViewModelMain, this)
        binding.idRvItemsMain.layoutManager = LinearLayoutManager(context)
        binding.idRvItemsMain.adapter = groceryRvAdapterMain
        groceryViewModelMain.getAllList().observe(viewLifecycleOwner, Observer {
            groceryRvAdapterMain.list = it
            groceryRvAdapterMain.notifyDataSetChanged()
        })

        binding.idFABaddMain.setOnClickListener {
            openDialog()
        }
    }

    fun openDialog() {
        AddRangeDialogMain(requireContext(), object: GroceryRvAdapterMain.GroceryItemClickInterface {
            override fun onItemClick(groceryListItems: GroceryListItems) {
                groceryViewModelMain.insertList(groceryListItems)
            }
        }).show()

    }

    override fun onItemClick(groceryListItems: GroceryListItems) {
        groceryViewModelMain.deleteList(groceryListItems)
        groceryRvAdapterMain.notifyDataSetChanged()
        Toast.makeText(context, "Item deleted...", Toast.LENGTH_SHORT)
    }

    override fun respond(position: Int, name: String?) {
        Log.d("respond", "$position $name")

//        val navHostFragment = supportFragmentManager.find
//
//        val action = MyNavDirections.actionGlobalMainFragment(position)
//        view?.findNavController().navigate(action)



        val fragmentSecond = MainFragment()
//        val bundle = Bundle()
//        bundle.putString("ID", position.toString())
//        bundle.putString("NAME", name)
//        fragmentSecond.arguments = bundle
    }

//    fun onItemClickRoot(id: Int) {
//
//        Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_itemSecondFragment)
//    }
}