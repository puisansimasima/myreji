package com.example.myrejiapplication.data

import androidx.lifecycle.MutableLiveData
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.NumberFormat

@Entity(tableName = "Item")
data class Item (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "name")
    val itemName: String,
    @ColumnInfo(name = "price")
    val itemPrice: Double,
    @ColumnInfo(name = "category")
    val categoryName: String,
)

fun Item.getFormattedPrice(): String =
    NumberFormat.getCurrencyInstance().format(itemPrice)

data class UserItem(
    val id: Int,
    val itemName: String,
    val itemPrice: Double,
    val categoryName: String,
    val isChecked: MutableLiveData<Boolean> = MutableLiveData(false)

)

data class TableName(
    val tableId:Int,
    val tableName:String
)

//data class FirebaseItem(
  //  val itemName_firebase: String="",


//)