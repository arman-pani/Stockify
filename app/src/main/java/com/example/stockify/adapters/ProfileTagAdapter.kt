package com.example.stockify.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.stockify.databinding.ItemFilterBinding
import com.example.stockify.utils.ProfileTag

class ProfileTagAdapter(
    private val tags: List<ProfileTag>,
    private val onTagClickListener: (ProfileTag) -> Unit
) : RecyclerView.Adapter<ProfileTagAdapter.ViewHolder>(){

    private var selectedPosition = 0

    inner class ViewHolder(val binding: ItemFilterBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(tag: ProfileTag, position: Int){
            binding.filterText.text = tag.label

            binding.filterContainer.isSelected = (selectedPosition == position)
            binding.filterText.setTextColor(
                if (selectedPosition == position) Color.WHITE else Color.BLACK
            )

            binding.root.setOnClickListener{
                val previous = selectedPosition
                selectedPosition = position
                notifyItemChanged(previous)
                notifyItemChanged(position)
                onTagClickListener(tag)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemFilterBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(tags[position], position)
    }
    override fun getItemCount() = tags.size
}