package com.example.stockify.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.stockify.R
import com.example.stockify.activities.StockProfileActivity
import com.example.stockify.databinding.CardStockBinding
import com.example.stockify.models.StockModel
import com.example.stockify.utils.loadImage
import com.example.stockify.viewModels.StockProfileViewModel

class StockItemAdapter(
    private val stockItems: List<StockModel>,
    private val onItemClick: (StockModel) -> Unit,
) : RecyclerView.Adapter<StockItemAdapter.ViewHolder>(){

    private lateinit var stockViewModel: StockProfileViewModel

    class ViewHolder(val binding: CardStockBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CardStockBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = stockItems[position]
        holder.binding.companyNameTextView.text = item.companyName
        holder.binding.companySymbolTextView.text = item.companySymbol
        if (item.percentage < 0) {
            holder.binding.percentageTextView.setTextColor(holder.binding.percentageTextView.context.getColor(R.color.red))
            holder.binding.percentageTextView.text = String.format("-%.2f%%", -item.percentage)
        } else {
            holder.binding.percentageTextView.setTextColor(holder.binding.percentageTextView.context.getColor(R.color.green))
            holder.binding.percentageTextView.text = String.format("+%.2f%%", item.percentage)
        }
        holder.binding.stockPriceTextView.text = String.format("$%.2f", item.stockPrice)
        holder.binding.companyLogoImageView.loadImage(item.companyLogoUrl)

        holder.binding.root.setOnClickListener {
            onItemClick(stockItems[position])
            val intent = Intent(holder.binding.root.context, StockProfileActivity::class.java)
            holder.binding.root.context.startActivity(intent)
        }
    }

    override fun getItemCount() = stockItems.size
}