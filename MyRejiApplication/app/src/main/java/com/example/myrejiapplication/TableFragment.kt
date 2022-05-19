package com.example.myrejiapplication

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.snapshotFlow
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myrejiapplication.data.TableName
import com.example.myrejiapplication.databinding.FragmentTableBinding
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.NonCancellable.children
import org.json.JSONArray
import org.json.JSONObject


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TableFragment.newInstance] factory method to
 * create an instance of this fragment.
 */




class TableFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private var _binding: FragmentTableBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTableBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tableDetailButton.setOnClickListener {

            val action=TableFragmentDirections.actionTableFragmentToTableDetailFragment()
                findNavController().navigate(action)
        }
     //   binding.addTableButton.setOnClickListener {
         //
     //   }


        val itemDecoration = DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL)

        //recyclerviewを押したら画面遷移

        val adapter = TableListAdapter {
            val action =
                TableFragmentDirections.actionTableFragmentToTableDetailFragment()
            this.findNavController().navigate(action)
        }

        adapter.setOnViewClickListener(
            // インターフェースの再利用は想定しておらず、その場限りでしか使わないためobject式として宣言
            object : TableListAdapter.OnItemViewClickListener {

                override fun onItemClick(tableName: TableName) {
                    Log.d("TAG-clickitem", tableName .toString())
                    //処理

                }

            }
        )

        // Attach an observer on the allItems list to update the UI automatically when the data
        // changes.

                //firebaseのtableにリスナーを設置する
                FirebaseDatabase.getInstance().getReference("table").addValueEventListener(
                    object : ValueEventListener {

                        override fun onDataChange(dataSnapshot: DataSnapshot) {

                            val items: HashMap<String ,String>? = dataSnapshot.getValue(object :
                                GenericTypeIndicator<HashMap<String, String>>() {})

                            Log.d("TAG=hash", items.toString())

                            val keyList = items?.keys?.let { ArrayList(it) }?.sorted()

                            Log.d("TAG=hash", keyList.toString())


                            for (child in dataSnapshot.children){

                                Log.d("TAG-",(child.key.toString()))
                              //  Log.d("TAG-",(child.children.toString() as String))
                            }

                            val _fireList = items?.values?.let { ArrayList(it).toList() }!!
                                Log.d("TAG-li",_fireList.toString())
                            val database = Firebase.database

                        }

                        override fun onCancelled(error: DatabaseError) {

                            //       TODO("Not yet implemented")
                        }

                    }

                )


        //firebaseのlistにリスナー設置する
        FirebaseDatabase.getInstance().getReference("list").addValueEventListener(
            object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {

                    Log.d("TAG-","kaeatta--")

                 //   val items: HashMap<String ,String>? = snapshot.getValue(object :
                   //     GenericTypeIndicator<HashMap<String,String>>() {})

                 //   Log.d("TAG-hash2", items.toString())
                   val AZ= FirebaseDatabase.getInstance().getReference("list")
                    AZ.child("list").key

                    ; // will have value of String: "your-project-name"
                    Log.d("TAG-drophash2",snapshot.children.drop(0).toString())
                    Log.d("TAG-tolisthash2",snapshot.children.toList() .toString())
                    val aaa=snapshot.children.toList()

                    Log.d("TAG-L",aaa[1].value .toString())

                    val bbb= snapshotFlow { snapshot.children }
                  //  val _fireList = bbb.let { ArrayList(it).toList() }!!
                    for (child in snapshot.children){
                        Log.d("TAG-",(child.key.toString()))

                        Log.d("TAG-list",(listOf( child.value).toString()))

                    }

                    Log.d("TAG-hash2",AZ .toString())
                    Log.d("TAG-hash2",snapshot.children.toString())
                    Log.d("TAG-hash2",snapshot.childrenCount .toString())
                    Log.d("TAG-hash2",snapshot.value.toString())
                    Log.d("TAG-bb",bbb .toString())

                    val resultMap=HashMap<String, String>()
                    val tList=ArrayList<HashMap<String, String>>()



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


        binding.tableRecyclerView.setHasFixedSize(true)
        binding.tableRecyclerView.addItemDecoration(itemDecoration)
        binding.tableRecyclerView.layoutManager = LinearLayoutManager(this.context)
        binding.tableRecyclerView.adapter = adapter


        val swipeToDismissTouchHelper = getSwipeToDismissTouchHelper(adapter)
        swipeToDismissTouchHelper.attachToRecyclerView(binding.tableRecyclerView)

        adapter.submitList(listOf("1","2"))
        // Attach an observer on the allItems list to update the UI automatically when the data
        // changes.
      //  viewModel.allItems.observe(this.viewLifecycleOwner) { items ->
      //      items.let {
         //       adapter.submitList(it)
        //    }
   //     }
    }






    //カードのスワイプアクションの定義
    private fun getSwipeToDismissTouchHelper(adapter: TableListAdapter) =
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            //スワイプ時に実行
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                //データリストからスワイプしたデータを削除

                val position: Int = viewHolder.bindingAdapterPosition

                Log.d("TAG-posi",position.toString())
             //   Log.d("TAG-posialbum", album[position]. toString())
                // getItemId(position)


                val list=ItemListAdapter{

                }

                adapter.notifyItemRemoved(viewHolder.bindingAdapterPosition)
            }

            // private fun deleteItem(item: Item) {

            //  }



            //スワイプした時の背景を設定
            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {
                super.onChildDraw(
                    c,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )
                val itemView = viewHolder.itemView
                val background = ColorDrawable()
                background.color = Color.parseColor("#f44336")
                if (dX < 0)
                    background.setBounds(
                        itemView.right + dX.toInt(),
                        itemView.top,
                        itemView.right,
                        itemView.bottom
                    )
                else
                    background.setBounds(
                        itemView.left,
                        itemView.top,
                        itemView.left + dX.toInt(),
                        itemView.bottom
                    )

                background.draw(c)
            }
        })


    fun makeList(list: ArrayList<String>) {

        Log.d("TAGvallist", list.toString())

    }



    companion object {
            /**
             * Use this factory method to create a new instance of
             * this fragment using the provided parameters.
             *
             * @param param1 Parameter 1.
             * @param param2 Parameter 2.
             * @return A new instance of fragment TableFragment.
             */
            // TODO: Rename and change types and number of parameters
            @JvmStatic
            fun newInstance(param1: String, param2: String) =
                TableFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
        }
 }




