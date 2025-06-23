package com.example.stockify.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.stockify.R
import com.example.stockify.databinding.CardStockBinding
import com.example.stockify.databinding.CardWishlistBinding
import com.example.stockify.room.WatchlistItemModel
import com.example.stockify.utils.loadImage

class WatchlistAdapter(
    private val watchlist: List<WatchlistItemModel>,
    private val viewType: ViewType,
): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    enum class ViewType{
        HORIZONTAL, VERTICAL
    }

    inner class VerticalViewHolder(val binding: CardStockBinding) : RecyclerView.ViewHolder(binding.root)
    inner class HorizontalViewHolder(val binding: CardWishlistBinding) : RecyclerView.ViewHolder(binding.root)

    override fun getItemViewType(position: Int): Int {
        return viewType.ordinal
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(ViewType.values()[viewType]){
            ViewType.HORIZONTAL -> {
                val binding = CardWishlistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                HorizontalViewHolder(binding)
            }
            ViewType.VERTICAL -> {
                val binding = CardStockBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                VerticalViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = watchlist[position]
        val context = holder.itemView.context

        when(holder){
            is HorizontalViewHolder -> {
                holder.binding.apply {
                    companyNameTextView.text = item.companyName
                    companySymbolTextView.text = item.companySymbol
                    percentageTextView.setTextColor(context.getColor(if (item.percentage < 0) R.color.red else R.color.green))
                    percentageTextView.text = (if (item.percentage < 0) "-" else "+") + "${kotlin.math.abs(item.percentage)}%"
                    companyLogoImageView.loadImage(item.companyLogo)
                }
        }
            is VerticalViewHolder -> {
                holder.binding.apply {
                    companyNameTextView.text = item.companyName
                    companySymbolTextView.text = item.companySymbol
                    stockPriceTextView.text = "$${item.stockValue}"
                    percentageTextView.setTextColor(context.getColor(if (item.percentage < 0) R.color.red else R.color.green))
                    percentageTextView.text = (if (item.percentage < 0) "-" else "+") + "${kotlin.math.abs(item.percentage)}%"
                    companyLogoImageView.loadImage(item.companyLogo)
                }
            }
        }
    }

    override fun getItemCount() = watchlist.size


}