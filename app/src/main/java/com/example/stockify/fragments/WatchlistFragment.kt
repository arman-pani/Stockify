package com.example.stockify.fragments

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.stockify.R
import com.example.stockify.adapters.WatchlistAdapter
import com.example.stockify.databinding.FragmentWatchlistBinding
import com.example.stockify.models.StockModel
import com.example.stockify.models.WishlistModel

class WatchlistFragment: Fragment(R.layout.fragment_watchlist) {

    private var _binding: FragmentWatchlistBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: WatchlistAdapter
    private lateinit var recyclerView: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWatchlistBinding.inflate(inflater, container, false)
        recyclerView = binding.watchlistRecyclerView
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(
            context, androidx.recyclerview.widget.LinearLayoutManager.VERTICAL, false
        )

        val watchlist = mutableListOf(
            StockModel("Tesla Inc.", "TSLA", "https://logo.clearbit.com/tesla.com", 4.5, 700.0),
            StockModel("Amazon.com Inc.", "AMZN", "https://logo.clearbit.com/amazon.com", 2.8, 3300.0),
            StockModel("Meta Platforms Inc.", "META", "https://logo.clearbit.com/meta.com", 1.2, 250.0)
        )

        adapter = WatchlistAdapter(watchlist) { position ->
            adapter.removeItem(position)
        }

        recyclerView.adapter = adapter

        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder
            ) = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                adapter.removeItem(position)
            }

            override fun onChildDraw(
                c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder,
                dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean
            ) {
                val itemView = viewHolder.itemView
                val paint = Paint()
                paint.color = Color.parseColor("#E91E63")
                val background = RectF(
                    itemView.right + dX, itemView.top.toFloat(),
                    itemView.right.toFloat(), itemView.bottom.toFloat()
                )
                c.drawRect(background, paint)

                val icon = ContextCompat.getDrawable(recyclerView.context, R.drawable.increase)!!
                val iconMargin = (itemView.height - icon.intrinsicHeight) / 2
                val iconTop = itemView.top + (itemView.height - icon.intrinsicHeight) / 2
                val iconBottom = iconTop + icon.intrinsicHeight
                val iconLeft = itemView.right - iconMargin - icon.intrinsicWidth
                val iconRight = itemView.right - iconMargin
                icon.setBounds(iconLeft, iconTop, iconRight, iconBottom)
                icon.draw(c)

                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
            }
        }

        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)

        return binding.root
    }
}