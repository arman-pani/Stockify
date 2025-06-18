package com.example.stockify

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class WishlistItemAdapter(private val wishlistItems: List<WishlistModel>) : RecyclerView.Adapter<WishlistItemAdapter.ViewHolder>(){

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val companyNameTextView: TextView = itemView.findViewById(R.id.companyNameTextView)
        val companySymbolTextView: TextView = itemView.findViewById(R.id.companySymbolTextView)
        val companyLogoImageView: ImageView = itemView.findViewById(R.id.companyLogoImageView)
        val percentageTextView: TextView = itemView.findViewById(R.id.percentageTextView)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_wishlist, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = wishlistItems[position]
        holder.companyNameTextView.text = item.companyName
        holder.companySymbolTextView.text = item.companySymbol
        holder.percentageTextView.text = "${item.percentage}%"
    }

    override fun getItemCount() = wishlistItems.size
}