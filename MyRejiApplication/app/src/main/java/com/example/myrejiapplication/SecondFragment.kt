package com.example.myrejiapplication

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.myrejiapplication.databinding.FragmentSecondBinding
import com.example.myrejiapplication.data.Item
/**
 * Fragment to add or update an item in the Inventory database.
 */

class SecondFragment : Fragment() {

    //private val navigationArgs: ItemDetailFragmentArgs by navArgs()
    lateinit var item: Item
    //val allItems: LiveData<List<Item>> = itemDao.getItems().asLiveData()
    private val viewModel: InventoryViewModel by activityViewModels {
        InventoryViewModelFactory(
            (activity?.application as InventoryApplication).database.itemDao()
        )
    }
    // Binding object instance corresponding to the fragment_add_item.xml layout
    // This property is non-null between the onCreateView() and onDestroyView() lifecycle callbacks,
    // when the view hierarchy is attached to the fragment
    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    /**
     * Returns true if the EditTexts are not empty
     */
    private fun isEntryValid(): Boolean {
        return viewModel.isEntryValid(

            binding.itemName.text.toString(),
            binding.itemPrice.text.toString(),
            binding.categoryName.text.toString(),
        )
    }



    /**
     * Inserts the new Item into database and navigates up to list fragment.
     */
    private fun addNewItem() {
        if (isEntryValid()) {
            viewModel.addNewItem(
                itemId = id,
                binding.itemName.text.toString(),
                binding.itemPrice.text.toString(),
                binding.categoryName.text.toString(),
            )

            val action = SecondFragmentDirections.actionSecondFragmentToFirstFragment()
            findNavController().navigate(action)
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.saveButton.setOnClickListener {
            Log.d("TAG",_binding.toString())
            Log.d("TAG","nururu--")





            addNewItem()
           // findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
            //addNewItem()

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}