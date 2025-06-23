package com.example.stockify.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.stockify.adapters.StockItemAdapter.ViewHolder
import com.example.stockify.databinding.CardStockBinding
import com.example.stockify.databinding.ProfileRowLayoutBinding
import com.example.stockify.models.ProfileRowModel

class ProfileRowAdapter(private val profileRows: List<ProfileRowModel>) : RecyclerView.Adapter<ProfileRowAdapter.ViewHolder>() {

    class ViewHolder(val binding: ProfileRowLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileRowAdapter.ViewHolder {
        val binding = ProfileRowLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = profileRows[position]
        holder.binding.labelTextView.text = item.label
        holder.binding.iconImageView.setImageResource(item.icon)
    }

    override fun getItemCount() = profileRows.size
}