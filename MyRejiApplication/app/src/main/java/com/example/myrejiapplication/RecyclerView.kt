package com.example.myrejiapplication

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner.current
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myrejiapplication.data.Item
import com.example.myrejiapplication.data.getFormattedPrice
import com.example.myrejiapplication.databinding.CategoryTextItemListViewBinding

/**
 * [ListAdapter] implementation for the recyclerview.
 */


//onCreateViewHolderメソッドでは、レイアウトの設定を行います。
// xmlで実装したレイアウトファイルからViewを生成し、先ほどのViewHolderに引数として渡します。
 //var itemViewLst:Int

var album = mutableListOf<Item>()

class ItemListAdapter( val onItemClicked: (Item) -> Unit) :
    ListAdapter<Item, ItemListAdapter.ItemViewHolder>(DiffCallback) {

    // 1. リスナを格納する変数を定義（lateinitで初期化を遅らせている）
    lateinit var listener: OnItemViewClickListener

    // 2. インターフェースを作成
    interface OnItemViewClickListener {
        fun onItemClick(item: Item)
    }

    // 3. リスナーをセット
    fun setOnViewClickListener(listener: ItemListAdapter.OnItemViewClickListener) {
        // 定義した変数listenerに実行したい処理を引数で渡す（Fragmentで渡している）
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        return ItemViewHolder(
            CategoryTextItemListViewBinding.inflate(
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

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val current = getItem(position)



// セルがクリックされた時にインターフェースの処理が実行される
        holder.itemView.setOnClickListener {
            onItemClicked(current)
            listener.onItemClick(current)

        }

        holder.bind(current)


        album.add(current)
    }

        //コンストラクタで渡されるアイテム1件（1行）の各ビュー要素

        class ItemViewHolder(private var binding: CategoryTextItemListViewBinding) :
            RecyclerView.ViewHolder(binding.root) {

           //val _itemId=binding.itemId
            fun bind(item: Item) {
                binding.itemId.text = item.id.toString()
                binding.itemName.text = item.itemName
                binding.itemPrice.text = item.getFormattedPrice()
                binding.categoryName.text = item.categoryName

            }

        }


     //override fun getItemId(position: Int): Long {
     //    var album = mutableListOf<Long>()
      //   album.addAll(itemId)
    //  return (album.coverUrl + album.title).hashCode()
   //  }


    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Item>() {
            override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem.itemName == newItem.itemName
            }
        }
    }

}