package com.example.stockify.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.stockify.R
import com.example.stockify.models.StockModel

class WatchlistAdapter(private val watchlist: MutableList<StockModel>, private val onDelete: (Int) -> Unit): RecyclerView.Adapter<WatchlistAdapter.ViewHolder>(){

    class ViewHolder(val itemView: View): RecyclerView.ViewHolder(itemView){
        val companyNameTextView: TextView = itemView.findViewById(R.id.companyNameTextView)
        val companySymbolTextView: TextView = itemView.findViewById(R.id.companySymbolTextView)
        val companyLogoImageView: ImageView = itemView.findViewById(R.id.companyLogoImageView)
        val percentageTextView: TextView = itemView.findViewById(R.id.percentageTextView)
        val stockPriceTextView: TextView = itemView.findViewById(R.id.stockPriceTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_stock, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = watchlist[position]
        holder.companyNameTextView.text = item.companyName
        holder.companySymbolTextView.text = item.companySymbol
        if (item.percentage < 0) {
            holder.percentageTextView.setTextColor(holder.itemView.context.getColor(R.color.red))
            holder.percentageTextView.text = "-${kotlin.math.abs(item.percentage)}%"
        } else {
            holder.percentageTextView.setTextColor(holder.itemView.context.getColor(R.color.green))
            holder.percentageTextView.text = "+${item.percentage}%"
        }
        holder.stockPriceTextView.text = "$${item.stockPrice}"
    }

    override fun getItemCount() = watchlist.size

    fun removeItem(position: Int) {
        watchlist.removeAt(position)
        notifyItemRemoved(position)
    }

}