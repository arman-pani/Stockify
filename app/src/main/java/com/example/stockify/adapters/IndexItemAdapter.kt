package com.example.stockify.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.stockify.R
import com.example.stockify.models.IndexModel

class IndexItemAdapter(private val indexItems: List<IndexModel>) : RecyclerView.Adapter<IndexItemAdapter.ViewHolder>(){
    class ViewHolder(val itemView: View): RecyclerView.ViewHolder(itemView){
        val indexNameTextView: TextView = itemView.findViewById(R.id.indexNameTextView);
        val indexValueTextView: TextView = itemView.findViewById(R.id.indexValueTextView);
        val indexLogoImageView: ImageView = itemView.findViewById(R.id.indexLogoImageView);
        val valueChangeTextView: TextView = itemView.findViewById(R.id.valueChangeTextView);
        val percentageChangeTextView: TextView = itemView.findViewById(R.id.percentageTextView);
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_index, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = indexItems[position]
        holder.indexNameTextView.text = item.indexName
        holder.indexValueTextView.text = item.indexValue.toString()
     val valueChange = item.valueChange
     val percentageChange = item.percentageChange

     holder.valueChangeTextView.text = if (valueChange >= 0) "+$valueChange" else "$valueChange"
     holder.valueChangeTextView.setTextColor(
         if (valueChange >= 0) holder.itemView.context.getColor(R.color.green) else holder.itemView.context.getColor(R.color.red)
     )

     holder.percentageChangeTextView.text = if (percentageChange >= 0) "+${percentageChange}%" else "${percentageChange}%"
     holder.percentageChangeTextView.setTextColor(
         if (percentageChange >= 0) holder.itemView.context.getColor(R.color.green) else holder.itemView.context.getColor(R.color.red)
     )
    }

    override fun getItemCount() = indexItems.size
}