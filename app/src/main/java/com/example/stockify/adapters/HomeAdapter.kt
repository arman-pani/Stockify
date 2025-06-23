package com.example.stockify.adapters

import android.view.GestureDetector.OnDoubleTapListener
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.stockify.R
import com.example.stockify.models.IndexModel
import com.example.stockify.models.StockModel
import com.example.stockify.room.WatchlistItemModel
import com.example.stockify.utils.FilterType

object ViewType {
    const val SEARCH_BAR = 0
    const val SECTION_HEADER = 1
    const val HORIZONTAL_LIST = 2
    const val VERTICAL_LIST = 3
}

enum class ListType {
    INDICES,
    WATCHLIST,
    FILTER
}

sealed class HomeItem {
    object SearchBar : HomeItem()
    data class SectionHeader(val title: String, val iconRes: Int) : HomeItem()
    data class HorizontalList(val type: ListType, val items: List<Any>) : HomeItem()
    data class VerticalList(val items: List<Any>) : HomeItem()
}

class SearchBarViewHolder(view: View) : RecyclerView.ViewHolder(view)

class SectionHeaderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val headerText: TextView = view.findViewById(R.id.headerText)
    private val headerIcon: ImageView = view.findViewById(R.id.headerIcon)
    fun bind(item: HomeItem.SectionHeader) {
        headerText.text = item.title
        headerIcon.setImageResource(item.iconRes)
    }
}


class HorizontalListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val recyclerView: RecyclerView = view.findViewById(R.id.innerRecyclerView)

    fun bind(items: List<Any>, adapter: RecyclerView.Adapter<*>) {
        recyclerView.layoutManager = LinearLayoutManager(itemView.context, RecyclerView.HORIZONTAL, false)
        recyclerView.adapter = adapter
    }
}

class VerticalListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val recyclerView: RecyclerView = view.findViewById(R.id.innerRecyclerView)

    fun bind(items: List<Any>, adapter: RecyclerView.Adapter<*>) {
        recyclerView.layoutManager = LinearLayoutManager(itemView.context)
        recyclerView.adapter = adapter
    }
}




class HomeAdapter(
    private val items: List<HomeItem>,
    private val onFilterSelected: (FilterType) -> Unit,
    private val onStockItemTapListener: (StockModel) -> Unit,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is HomeItem.SearchBar -> ViewType.SEARCH_BAR
            is HomeItem.SectionHeader -> ViewType.SECTION_HEADER
            is HomeItem.HorizontalList -> ViewType.HORIZONTAL_LIST
            is HomeItem.VerticalList -> ViewType.VERTICAL_LIST
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            ViewType.SEARCH_BAR -> {
                val view = inflater.inflate(R.layout.search_bar, parent, false)
                SearchBarViewHolder(view)
            }

            ViewType.SECTION_HEADER -> {
                val view = inflater.inflate(R.layout.view_section_header, parent, false)
                SectionHeaderViewHolder(view)
            }

            ViewType.HORIZONTAL_LIST -> {
                val view = inflater.inflate(R.layout.item_inner_recyclerview, parent, false)
                HorizontalListViewHolder(view)
            }

            ViewType.VERTICAL_LIST -> {
                val view = inflater.inflate(R.layout.item_inner_recyclerview, parent, false)
                VerticalListViewHolder(view)
            }

            else -> throw IllegalArgumentException("Unknown view type")
        }
    }

    override fun getItemCount(): Int = items.size



    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = items[position]) {
            is HomeItem.SearchBar -> Unit
            is HomeItem.SectionHeader -> (holder as SectionHeaderViewHolder).bind(item)
            is HomeItem.HorizontalList -> {
                val horizontalHolder = holder as HorizontalListViewHolder
                when (item.type) {
                    ListType.INDICES -> horizontalHolder.bind(item.items, IndexItemAdapter(item.items as List<IndexModel>))
                    ListType.WATCHLIST -> horizontalHolder.bind(item.items, WatchlistAdapter(item.items as List<WatchlistItemModel>, WatchlistAdapter.ViewType.HORIZONTAL))
                    ListType.FILTER -> horizontalHolder.bind(item.items, FilterAdapter(item.items as List<FilterType>, onFilterSelected))
                }
            }
            is HomeItem.VerticalList -> (holder as VerticalListViewHolder).bind(item.items, StockItemAdapter(item.items as List<StockModel>, onStockItemTapListener))
        }
    }
}


