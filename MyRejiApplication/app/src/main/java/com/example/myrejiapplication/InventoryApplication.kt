package com.example.myrejiapplication

import android.app.Application
import com.example.myrejiapplication.data.AppDatabase


class InventoryApplication : Application(){
    //初めて呼び出されたときに初期化
    val database: AppDatabase by lazy { AppDatabase.getDatabase(this) }



}