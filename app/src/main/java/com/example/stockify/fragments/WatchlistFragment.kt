package com.example.stockify.fragments

import android.R
import android.graphics.Canvas
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.stockify.adapters.WatchlistAdapter
import com.example.stockify.databinding.FragmentWatchlistBinding
import com.example.stockify.room.WatchlistItemModel
import com.example.stockify.viewModels.WatchlistViewModel
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator
import java.util.Collections


class WatchlistFragment: Fragment(com.example.stockify.R.layout.fragment_watchlist) {

    private var _binding: FragmentWatchlistBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: WatchlistAdapter
    private lateinit var recyclerView: RecyclerView

    private lateinit var viewModel: WatchlistViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWatchlistBinding.inflate(inflater, container, false)
        recyclerView = binding.watchlistRecyclerView
        recyclerView.layoutManager = object: LinearLayoutManager(context){
            override fun canScrollVertically(): Boolean = false
        }

        viewModel = ViewModelProvider(this)[WatchlistViewModel::class.java]

        viewModel.getWatchlist.observe(viewLifecycleOwner){ watchlist ->
            adapter = WatchlistAdapter(watchlist, WatchlistAdapter.ViewType.VERTICAL)
            recyclerView.adapter = adapter
        }


        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT){
            override fun onMove(
                recyclerView: RecyclerView,
                source: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
//                val sourcePosition = source.adapterPosition
//                val targetPosition = target.adapterPosition
//
//                Collections.swap(watchlist, sourcePosition, targetPosition)
//                adapter.notifyItemMoved(sourcePosition,targetPosition)
                return true

            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                viewModel.onDeleteItem(position)
                adapter.notifyItemRemoved(position)
            }

            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {

                RecyclerViewSwipeDecorator.
                Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                    .addBackgroundColor(
                        ContextCompat.getColor(
                            context,
                            R.color.holo_red_dark
                        )
                    )
                    .addActionIcon(R.drawable.ic_menu_add)
                    .create()
                    .decorate()

                super.onChildDraw(
                    c,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )
            }

        })
        itemTouchHelper.attachToRecyclerView(recyclerView)
        return binding.root
    }
}