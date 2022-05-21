package com.example.myrejiapplication

import com.example.myrejiapplication.data.DataFireBase
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class AppFirebase {


    //firebaseに書き込み
     fun writeNewItem() {

        var table = DataFireBase.TableName(this).copy()

        fireDatabase= Firebase.database.reference




        //TableListに登録
       // fireDatabase.child("TableList").setValue(list)
       // TableNameに追加登録
      //  fireDatabase.child("tableName").push().setValue(tableName)


        //myRef1.setValue(list)


    }


}



