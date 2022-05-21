package com.example.myrejiapplication

import android.util.Log
import androidx.databinding.adapters.NumberPickerBindingAdapter.setValue
import com.example.myrejiapplication.data.DataFireBase
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class AppFirebase {


    //firebaseに書き込み
     fun writeNewItem(addTableName: String) {

        fireDatabase= Firebase.database.reference

        FirebaseDatabase.getInstance().getReference("shop").addValueEventListener(
            object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {

                    fireDatabase.child("shop"). child("TableList").setValue(addTableName)
                   //


                    //   val items: HashMap<String ,String>? = snapshot.getValue(object :
                    //     GenericTypeIndicator<HashMap<String,String>>() {})

                    //   Log.d("TAG-hash2", items.toString())

                    ; // will have value of String: "your-project-name"
                    Log.d("TAG-hash2",snapshot.key .toString())


                    val resultMap=HashMap<String, String>()
                    val tList=ArrayList<HashMap<String, String>>()

                    //TableListに登録
                    //
                    // TableNameに追加登録
                    //  fireDatabase.child("tableName").push().setValue(tableName)

                    //   HashMap<String, String> resultMap = new HashMap<String, String>();
                    //  　　　　List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

                    //  for (i in snapshot : DataSnapshot) {
                    //   snapshot.getKey() // will have value of String: "users", then "books"
                    //    for (DataSnapshot deeperSnapshot : dataSnapshot) {
                    //   snapshot.getKey();
                    // if snapshot.getKey() is "users", this will have value of String: "randomUserId1", then "randomUserId2"
                    // If snapshot.getKey() is "books", this will have value of String: "bookId1", then "bookId2"
                    //  }

                    // }


                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }


            }
        )


        FirebaseDatabase.getInstance().getReference("tableName").addValueEventListener(
            object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {

                    Log.d("TAG-","kaeatta--")
                    fireDatabase.child("shop"). child("TableName").push().setValue(addTableName)
                    //   val items: HashMap<String ,String>? = snapshot.getValue(object :
                    //     GenericTypeIndicator<HashMap<String,String>>() {})

                    //   Log.d("TAG-hash2", items.toString())

                    ; // will have value of String: "your-project-name"
                    Log.d("TAG-hash2",snapshot.key .toString())


                    val resultMap=HashMap<String, String>()
                    val tList=ArrayList<HashMap<String, String>>()

                    val items: HashMap<String, String>? = snapshot.getValue(object :

                        GenericTypeIndicator<HashMap<String, String>>() {})


                    val valueList = items?.values?.let { ArrayList(it) }

               //     val _fireList = items?.keys?.let { ArrayList(it) }!!

                    //TableListに登録
                    // fireDatabase.child("TableList").setValue(list)
                    // TableNameに追加登録
                    //  fireDatabase.child("tableName").push().setValue(tableName)

                    //   HashMap<String, String> resultMap = new HashMap<String, String>();
                    //  　　　　List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

                    //  for (i in snapshot : DataSnapshot) {
                    //   snapshot.getKey() // will have value of String: "users", then "books"
                    //    for (DataSnapshot deeperSnapshot : dataSnapshot) {
                    //   snapshot.getKey();
                    // if snapshot.getKey() is "users", this will have value of String: "randomUserId1", then "randomUserId2"
                    // If snapshot.getKey() is "books", this will have value of String: "bookId1", then "bookId2"
                    //  }

                    // }


                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }


            }
        )
     }
    //読み込み
    fun readItem(){



    }



}





        //myRef1.setValue(list)










