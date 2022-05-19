package com.example.myrejiapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myrejiapplication.data.TableName

import com.example.myrejiapplication.data.getFormattedPrice
import com.example.myrejiapplication.databinding.CategoryTextItemListViewBinding
import com.example.myrejiapplication.databinding.FragmentTableListViewBinding
import kotlinx.coroutines.NonDisposableHandle.parent


var tableList = mutableListOf<TableName>()

    class TableListAdapter( val onItemClicked: (TableName) -> Unit) :
        ListAdapter<TableName, TableListAdapter.TableViewHolder>(DiffCallback) {

        // 1. リスナを格納する変数を定義（lateinitで初期化を遅らせている）
        lateinit var listener: OnItemViewClickListener

        // 2. インターフェースを作成
        interface OnItemViewClickListener {
            fun onItemClick(item: TableName)
        }

        // 3. リスナーをセット
        fun setOnViewClickListener(listener: TableListAdapter.OnItemViewClickListener) {
            // 定義した変数listenerに実行したい処理を引数で渡す（Fragmentで渡している）
            this.listener = listener
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TableListAdapter.TableViewHolder {

            return TableListAdapter.TableViewHolder(
                FragmentTableListViewBinding.inflate(
                    LayoutInflater.from(
                        parent.context

                    )
                )
            )
        }
        //onBindViewHolderメソッドでは、1行分のレイアウトの詳細設定をします。
        // 1行ごとにこのメソッドは呼ばれ、どの行かは、positionで取得することができます。このpositionをもとに、
        // Listから対応する行のデータを取得し、
        // 各Viewの詳細設定を行います

        override fun onBindViewHolder(holder: TableListAdapter.TableViewHolder, position: Int) {
            val current = getItem(position)



// セルがクリックされた時にインターフェースの処理が実行される
            holder.itemView.setOnClickListener {
                onItemClicked(current)
                listener.onItemClick(current)

            }

            holder.bind(current)


            tableList.add(current)
        }




        //コンストラクタで渡されるアイテム1件（1行）の各ビュー要素

        class TableViewHolder(private var binding: FragmentTableListViewBinding) :
            RecyclerView.ViewHolder(binding.root) {

            //val _itemId=binding.itemId
            fun bind(tableName: TableName) {
                binding.tableId.text=tableName.tableId.toString()
                binding.tableName.text = tableName.tableName.toString()
            }

        }


        //override fun getItemId(position: Int): Long {
        //    var album = mutableListOf<Long>()
        //   album.addAll(itemId)
        //  return (album.coverUrl + album.title).hashCode()
        //  }


        companion object {
            private val DiffCallback = object : DiffUtil.ItemCallback<TableName>() {
                override fun areItemsTheSame(oldItem: TableName, newItem: TableName): Boolean {
                    return oldItem === newItem
                }

                override fun areContentsTheSame(oldItem: TableName, newItem: TableName): Boolean {
                    return oldItem.tableName  == newItem.tableName
                }
            }
        }

    }


