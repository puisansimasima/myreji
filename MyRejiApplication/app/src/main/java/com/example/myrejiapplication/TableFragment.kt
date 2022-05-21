package com.example.myrejiapplication

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.provider.Contacts
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.snapshotFlow
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.myrejiapplication.data.DataFireBase
import com.example.myrejiapplication.data.Item
import com.example.myrejiapplication.databinding.FragmentFirstBinding
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

    lateinit var item: Item
   // private var _binding: com.example.myrejiapplication.databinding.FragmentFirstBinding? = null
  //  private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentTableBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val itemDecoration = DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL)

        //recyclerviewを押したら画面遷移

        val adapter = ItemListAdapter {
            val action =
                FirstFragmentDirections.actionFirstFragmentToSecondFragment()

            this.findNavController().navigate(action)
        }

        adapter.setOnViewClickListener(
            // インターフェースの再利用は想定しておらず、その場限りでしか使わないためobject式として宣言
            object : ItemListAdapter.OnItemViewClickListener {

                override fun onItemClick(item: Item) {
                    Log.d("TAG-clickitem", item .toString())
                    //処理

                }

            }
        )

        binding.tableRecyclerView .setHasFixedSize(true)
        binding.tableRecyclerView   .addItemDecoration(itemDecoration)
        binding.tableRecyclerView .layoutManager = LinearLayoutManager(this.context)
        binding.tableRecyclerView.adapter = adapter


        val swipeToDismissTouchHelper = getSwipeToDismissTouchHelper(adapter)
        swipeToDismissTouchHelper.attachToRecyclerView(binding.tableRecyclerView)

        // Attach an observer on the allItems list to update the UI automatically when the data
        // changes.
     //   viewModel.allItems.observe(this.viewLifecycleOwner) { items ->
     //       items.let {
     //           adapter.submitList(it)
      //      }
     //   }

        binding.tableDetailButton .setOnClickListener {
            this.findNavController().navigate(R.id.action_TableFragment_to_TableDetailFragment)
        }

    }

    //カードのスワイプアクションの定義
    private fun getSwipeToDismissTouchHelper(adapter: RecyclerView.Adapter<ItemListAdapter.ItemViewHolder>) =
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
                //  Log.d("TAG-posialbum", album[position]. toString())
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }































       //         Log.d("TAG-hash2", AZ.toString())
      //  Log.d("TAG-hash2", snapshot.children.toString())
      //  Log.d("TAG-hash2", snapshot.childrenCount.toString())
     //   Log.d("TAG-hash2", snapshot.value.toString())
     //   Log.d("TAG-bb", bbb.toString())


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
 /*

   */