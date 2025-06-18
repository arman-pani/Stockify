package com.example.stockify

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StockItemAdapter(private val stockItems: List<StockModel>) : RecyclerView.Adapter<StockItemAdapter.ViewHolder>(){

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val companyNameTextView: TextView = view.findViewById(R.id.companyNameTextView)
        val companySymbolTextView: TextView = view.findViewById(R.id.companySymbolTextView)
        val companyLogoImageView: ImageView = view.findViewById(R.id.companyLogoImageView)
        val percentageTextView: TextView = view.findViewById(R.id.percentageTextView)
        val stockPriceTextView: TextView = view.findViewById(R.id.stockPriceTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_stock, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = stockItems[position]
        holder.companyNameTextView.text = item.companyName
        holder.companySymbolTextView.text = item.companySymbol
        holder.percentageTextView.text = "${item.percentage}%"
        holder.stockPriceTextView.text = "$${item.stockPrice}"
    }

    override fun getItemCount() = stockItems.size
}