package com.example.myrejiapplication

import android.util.Log
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.stream.Collectors.toList

class AppFirebase {

    //lateinit var snap:MutableList<Any>

    //firebaseに書き込み
     fun writeNewItem(addTableName: String) {

        fireDatabase = Firebase.database.reference
     //新規追加書き込みする（push）
           fireDatabase.child("shop"). child("TableName").push().setValue(addTableName)

        val postReference=FirebaseDatabase.getInstance().getReference("shop")

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                val post = dataSnapshot.value
                    Log.d("TAG-po",post .toString())

                val snap = mutableListOf(dataSnapshot.child("TableList"))
                    Log.d("TAG-snap",snap.toString())
               // snap.add(addTableName)
                    Log.d("TAG-snapadd",snap.toString())
                val items: HashMap<String ,Any>? = dataSnapshot.getValue(object :
                    GenericTypeIndicator<HashMap<String,Any>>() {})

                Log.d("TAG-items",items.toString())
                Log.d("TAG-items",post.toString())

             //   val resultMap=HashMap<String, String>()
              //  val tList=ArrayList<HashMap<String, String>>()

                searchItem(snap)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
               // Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
            }
        }

        postReference.addValueEventListener(postListener)
            Log.d("TAG-mm",postReference.child("TableList").key .toString())

           val Test = postReference.child("TableList").key




        //val myRef=FirebaseDatabase.getInstance().getReference("shop")

        //fireDatabase.child("shop").child("TableList").setValue(mlist)

        //shopに変更があれば自動で取得
      //  myRef.child("TableName") .addValueEventListener(
        //    object : ValueEventListener {
           //     override fun onDataChange(snapshot: DataSnapshot) {

                    //List形式に変換して更新書き込み（set）
              //      val tablelist=snapshot.child("TableList").value
                   // val mlist= mutableListOf(tablelist as Any)

                 //   Log.d("TAG-snap",snapshot.toString())
                 //   Log.d("TAG-key",myRef.child("TableList").key .toString())
                   //     Log.d("TAG-get",myRef.child("TableList").get().addOnSuccessListener {  } .toString())
                   // mlist.add(addTableName)

              //  }

              //  override fun onCancelled(error: DatabaseError) {
              //      TODO("Not yet implemented")
              //  }
  //  } )

    //読み込み
    fun readItem(){




                   //


                    //   val items: HashMap<String ,String>? = snapshot.getValue(object :
                    //     GenericTypeIndicator<HashMap<String,String>>() {})

                    //   Log.d("TAG-hash2", items.toString())

                    ; // will have value of String: "your-project-name"
                  //  Log.d("TAG-hash2",snapshot.key .toString())




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




            }



    //    FirebaseDatabase.getInstance().getReference("tableName").addValueEventListener(
        //    object : ValueEventListener {
         //       override fun onDataChange(snapshot: DataSnapshot) {

                //    Log.d("TAG-","kaeatta--")

                    //   val items: HashMap<String ,String>? = snapshot.getValue(object :
                    //     GenericTypeIndicator<HashMap<String,String>>() {})

                    //   Log.d("TAG-hash2", items.toString())

                    ; // will have value of String: "your-project-name"
               //     Log.d("TAG-hash2",snapshot.key .toString())


             //       val resultMap=HashMap<String, String>()
          //          val tList=ArrayList<HashMap<String, String>>()

              //      val items: HashMap<String, String>? = snapshot.getValue(object :

               //         GenericTypeIndicator<HashMap<String, String>>() {})


              //      val valueList = items?.values?.let { ArrayList(it) }

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

fun searchItem(snapshot: MutableList<DataSnapshot>) {



    Log.d("TAG-serch",snapshot.toString())
    Log.d("TAG-serch",snapshot.get(0) .toString())
   // for (i in post:Any?){

     //   if post.getKey() is "users", this will have value of String: "randomUserId1", then "randomUserId2"

 //   }

}



//      override fun onCancelled(error: DatabaseError) {
          //          TODO("Not yet implemented")
          //      }


          //  }
      //  )
   //  }









//}





        //myRef1.setValue(list)










