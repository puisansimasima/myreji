package com.example.myrejiapplication

import android.util.Log
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class AppFirebase {

    //var aaa:MutableList<String> = TODO()

    //firebaseに書き込み
     fun writeNewItem(addTableName: String) {

        fireDatabase = Firebase.database.reference
     //新規追加書き込みする（push）
           fireDatabase.child("shop"). child("TableName").push().setValue(addTableName)

        val postReference=FirebaseDatabase.getInstance().getReference("shop")

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI


                Log.d("TAG-hash2",dataSnapshot.children.toList() .toString())
                Log.d("TAG-hash2",dataSnapshot.value.toString())
                Log.d("TAG-hash2",dataSnapshot.key .toString())
                Log.d("TAG-hash2",dataSnapshot.child("TableList"). key .toString())
                Log.d("TAG-hash2",dataSnapshot.child("TableList") .value .toString())

                val zaq=dataSnapshot.child("TableList").value.toString()

                   var tableList= mutableListOf(zaq)



                Log.d("TAG-t",zaq.toString())
                Log.d("TAG-t",tableList.toString())

tableList.add(0,addTableName)
                Log.d("TAG-t",tableList.toString())

                val items: HashMap<String ,Any>? = dataSnapshot.child("shop").getValue(object :
                    GenericTypeIndicator<HashMap<String,Any>>() {})

            // val   map= mapOf(dataSnapshot.key)
         //     val  map1=map.filterKeys { key -> "Kotlin" in key }



                if (items != null) {
                    Log.d("TAG-pitems", items.values.toString())
                }

                if (items != null) {
                    Log.d("TAG-pitems", items.keys.toString())
                }

                Log.d("TAG-pitems",items.isNullOrEmpty() .toString())


                val post = dataSnapshot.value
                    Log.d("TAG-p1",post .toString())
Log.d("TAG-po",postReference.child("TableList").key .toString())
                //Log.d("TAG-p",dataSnapshot.value .toString())


                val snap = mutableListOf(dataSnapshot.child("shop").child("TableList").value)
                    Log.d("TAG-snap",snap.toString())
                //val snap1:Map<String,Any> = mapOf(dataSnapshot.child("shop").child("TableList").value.toString())
                        Log.d("TAG-p2", dataSnapshot.child("shop").child("TableList").toString())
                val a= dataSnapshot.child("List").value
                Log.d("TAG-p3", listOf(a) .toString())
                Log.d("TAG-p3", listOf(dataSnapshot.child("List") .value) .toString())

               // snap.add(addTableName)
                    //Log.d("TAG-snapadd",snap. toList().toString() )




             //   val resultMap=HashMap<String, String>()
              //  val tList=ArrayList<HashMap<String, String>>()

                //searchItem(snap)
            }


            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
               // Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
            }
        }

        postReference.addValueEventListener(postListener)


    //    fireDatabase.child("shop"). child("TableList").push().setValue(tableList)

        //子要素にアクセス

        val refMap: DatabaseReference = Firebase.database.getReference("shop")

        refMap.child("TableName") .addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(dataSnapshot: DataSnapshot, s: String?) {
                // 子要素が追加された時に呼ばれる
                // dataSnapshot : 追加された要素のKey-Value
                // s:追加された要素の一つ前の要素のkey名
                Log.d("TAG-seit", "ValueEventListener#onChildAdded")
                Log.d("TAG-s0",dataSnapshot.toString())
                Log.d("TAG-s1",dataSnapshot.key.toString())
                Log.d("TAG-s2",dataSnapshot.value.toString())
                Log.d("TAG-s3", (dataSnapshot.key.toString() to dataSnapshot.value.toString()).toString())
                Log.d("TAG-s4",s .toString())
                Log.d("TAG-s5",dataSnapshot.child("TableList") .toString())
                Log.d("TAG-s5",dataSnapshot.child("TableList").value .toString())


                val a=dataSnapshot.key.toString() to dataSnapshot.value.toString()




                val items: HashMap<Int ,String>? = dataSnapshot.child("shop").child("TableList") .getValue(object :
                    GenericTypeIndicator<HashMap<Int,String>>() {})

                if (items != null) {
                    Log.d("TAG-s6", items.keys.toString())
                }
                if (items != null) {
                    Log.d("TAG-s6", items.values.toString())
                }

                Log.d("TAG-s6", items?.values.toString())
            }

            override fun onChildChanged(dataSnapshot: DataSnapshot, s: String?) {
                // 子要素に変化があった場合に呼ばれる
                // dataSnapshot : 変化のあった子要素のKey-Value
                // s:変化のあった子要素の一つ前の子要素のKey名
                Log.d("seit", "ValueEventListener#onChildChanged")
            }

            override fun onChildRemoved(dataSnapshot: DataSnapshot) {
                // 子要素が削除された
                Log.d("seit", "ValueEventListener#onChildRemoved")
            }

            override fun onChildMoved(dataSnapshot: DataSnapshot, s: String?) {
                // 子要素の順番が変化した
                Log.d("seit", "ValueEventListener#onChildMoved")
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // サーバーエラーかもしくはセキュリティとデータべースルールによってデータにアクセスできない
                Log.d("seit", "ValueEventListener#onCancelled")
            }
        })


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




fun searchItem(snapshot: MutableList<Any?>) {



    Log.d("TAG-serch",snapshot.toString())
   // Log.d("TAG-serch",snapshot.toString())
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










