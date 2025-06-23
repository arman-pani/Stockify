package com.example.stockify.adapters

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.stockify.R
import com.example.stockify.databinding.ItemFilterBinding
import com.example.stockify.utils.FilterType

class FilterAdapter(
    private val filters: List<FilterType>,
    private val onFilterSelected: (FilterType) -> Unit
) : RecyclerView.Adapter<FilterAdapter.FilterViewHolder>() {

    private var selectedPosition = 0

    inner class FilterViewHolder(val binding: ItemFilterBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterViewHolder {
        val binding = ItemFilterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FilterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FilterViewHolder, @SuppressLint("RecyclerView") position: Int) {
        val filter = filters[position]
        holder.binding.filterText.text = filter.title

        holder.binding.filterContainer.isSelected = (selectedPosition == position)
        holder.binding.filterText.setTextColor(
            if (selectedPosition == position) Color.WHITE else Color.BLACK
        )

        holder.binding.root.setOnClickListener {
            val previous = selectedPosition
            selectedPosition = position
            notifyItemChanged(previous)
            notifyItemChanged(position)
            onFilterSelected(filter)
        }
    }



    override fun getItemCount() = filters.size
}
