package com.example.stockify.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.stockify.databinding.DetailRowLayoutBinding
import com.example.stockify.models.DetailRowModel

class DetailRowAdapter(private val detailRows: List<DetailRowModel>): RecyclerView.Adapter<DetailRowAdapter.DetailRowViewHolder>() {

    inner class DetailRowViewHolder(val binding: DetailRowLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailRowViewHolder {
        val binding = DetailRowLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return DetailRowViewHolder(binding)
    }


    override fun onBindViewHolder(holder: DetailRowViewHolder, position: Int) {
        val item = detailRows[position]
        holder.binding.titleTextView.text = item.title
        holder.binding.valueTextView.text = item.value
    }

    override fun getItemCount() = detailRows.size
}