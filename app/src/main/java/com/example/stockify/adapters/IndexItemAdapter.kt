package com.example.stockify.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.stockify.R
import com.example.stockify.databinding.CardIndexBinding
import com.example.stockify.models.IndexModel
import com.example.stockify.utils.loadImage

class IndexItemAdapter(private val indexItems: List<IndexModel>) : RecyclerView.Adapter<IndexItemAdapter.ViewHolder>(){

    class ViewHolder(val binding: CardIndexBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CardIndexBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = indexItems[position]
        holder.binding.indexNameTextView.text = item.indexName
        holder.binding.indexValueTextView.text = item.indexValue.toString()
        val valueChange = item.valueChange
        val percentageChange = item.percentageChange

        holder.binding.valueChangeTextView.text = if (valueChange >= 0) "+$valueChange" else "$valueChange"
        holder.binding.valueChangeTextView.setTextColor(
            if (valueChange >= 0) holder.itemView.context.getColor(R.color.green)
            else holder.itemView.context.getColor(R.color.red)
        )

        holder.binding.percentageTextView.text = if (percentageChange >= 0) "+${percentageChange}%" else "${percentageChange}%"
        holder.binding.percentageTextView.setTextColor(
            if (percentageChange >= 0) holder.itemView.context.getColor(R.color.green)
            else holder.itemView.context.getColor(R.color.red)
        )
        holder.binding.indexLogoImageView.loadImage(item.indexLogoUrl)
    }

    override fun getItemCount() = indexItems.size
}