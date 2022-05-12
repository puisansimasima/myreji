package com.example.myrejiapplication

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myrejiapplication.data.Item
import com.example.myrejiapplication.data.ItemDao
import com.example.myrejiapplication.databinding.FragmentFirstBinding

import kotlinx.coroutines.flow.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {
    private val viewModel: InventoryViewModel by activityViewModels {
        InventoryViewModelFactory(
            (activity?.application as InventoryApplication).database.itemDao()
        )
    }

    lateinit var item: Item
    private var _binding: com.example.myrejiapplication.databinding.FragmentFirstBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
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

        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.addItemDecoration(itemDecoration)
        binding.recyclerView.layoutManager = LinearLayoutManager(this.context)
        binding.recyclerView.adapter = adapter


        val swipeToDismissTouchHelper = getSwipeToDismissTouchHelper(adapter)
        swipeToDismissTouchHelper.attachToRecyclerView(binding.recyclerView)

        // Attach an observer on the allItems list to update the UI automatically when the data
        // changes.
        viewModel.allItems.observe(this.viewLifecycleOwner) { items ->
            items.let {
                 adapter.submitList(it)
            }
        }

        binding.buttonFirst.setOnClickListener {
            this.findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
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
                Log.d("TAG-posialbum", album[position]. toString())
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



    /**
     * Binds views with the passed in item data.
     */


        // ★ここ★
        //val helper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
        //    0, (ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT)


        //  val deleteIcon = AppCompatResources.getDrawable(
        //  this@FirstFragment,
        //  R.drawable.ic_baseline_delete_forever_24
        // )
        //  val iconMarginVertical =
        //      (viewHolder.itemView.height - deleteIcon!!.intrinsicHeight) / 2
        //  val itemHeight = itemView.bottom - itemView.top
        //   val deleteIconIntrinsicWidth = deleteIcon?.intrinsicWidth
        //   val deleteIconIntrinsicHeight = deleteIcon?.intrinsicHeight
        //   if (isFromLeftDirection) {
        //      val deleteIconTop =
        //          itemView.top + (itemHeight - deleteIconIntrinsicHeight) / 2
        //      val deleteIconMargin =
        //          (itemHeight - deleteIconIntrinsicHeight) / 2
        //      deleteIcon.setBounds(
        //          itemView.right - deleteIconMargin - deleteIconIntrinsicWidth,
        //         deleteIconTop,
        //          itemView.right - deleteIconMargin,
        //           deleteIconTop + deleteIconIntrinsicHeight
        //        )
        //     } else {
        //        deleteIcon.setBounds(
        //            itemView.left + iconMarginVertical,
        //            itemView.top + iconMarginVertical,
        //    itemView.left + iconMarginVertical + deleteIcon.intrinsicWidth,
        //          itemView.bottom - iconMarginVertical
        //   )
        //  }
        //   deleteIcon.draw(canvas)

        //  helper.attachToRecyclerView(mainRecyclerView)





        override fun onDestroyView() {
            super.onDestroyView()
            _binding = null
        }

    }






