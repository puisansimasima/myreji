package com.example.myrejiapplication

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.widget.Adapter
import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

import com.example.myrejiapplication.data.ItemDao
import com.example.myrejiapplication.data.Item
import com.google.firebase.FirebaseError
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


class InventoryViewModel(private val itemDao: ItemDao) : ViewModel() {




    // Cache all items form the database using LiveData.
    val viewList:Flow<List<Item>> = itemDao.getAll()

    val allItems: LiveData<List<Item>> = itemDao.getAll().asLiveData()

   // val allItemsFirebase:LiveData<List<FirebaseItem>>=

    //lateinit var flatList: List<Item>


    
    private fun insertItem(item: Item) {
        viewModelScope.launch {
            itemDao.insert(item)
        }
    }

    fun getItem(itemId: Int){

    }


    fun deleteItem(item: Item) {

        viewModelScope.launch {
            itemDao.delete(item)
        }
    }

    //fun deleteDatabaseItem()


    private fun getNewItemEntry(id: Int,itemName: String, itemPrice: String, categoryName: String): Item {
        return Item(
            id,
            itemName = itemName,
            itemPrice = itemPrice.toDouble(),
            categoryName = categoryName
        )
    }

    fun addNewItem(  id: Int,itemName: String, itemPrice: String, categoryName: String) {
        val newItem = getNewItemEntry( id,itemName, itemPrice, categoryName)
        insertItem(newItem)

        Log.d("TAG", "addNewItem(u,,)")
    }

    fun isEntryValid(itemName: String, itemPrice: String, categoryName: String): Boolean {
        if (itemName.isBlank()|| itemPrice.isBlank() || categoryName.isBlank()) {
            return false
        }
        return true
    }


}

    class InventoryViewModelFactory(private val itemDao: ItemDao) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(InventoryViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return InventoryViewModel(itemDao) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")

        }

    }
