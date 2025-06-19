package com.example.stockify.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.stockify.R
import com.example.stockify.adapters.IndexItemAdapter
import com.example.stockify.adapters.StockItemAdapter
import com.example.stockify.models.StockModel
import com.example.stockify.adapters.HomeWatchlistItemAdapter
import com.example.stockify.models.WishlistModel
import com.example.stockify.databinding.FragmentHomeBinding
import com.example.stockify.models.IndexModel

class HomeFragment : Fragment(R.layout.fragment_home) {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.indicesRecyclerView.layoutManager = LinearLayoutManager(
            context, LinearLayoutManager.HORIZONTAL, false
        )
        binding.wishlistRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.stocksRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        val indexItems = listOf(
            IndexModel("S&P 500", 4500.0, 50.0, 1.1, "https://logo.clearbit.com/spglobal.com"),
            IndexModel("NASDAQ", 15000.0, -100.0, -0.7, "https://logo.clearbit.com/nasdaq.com"),
            IndexModel("Dow Jones", 35000.0, 200.0, 0.6, "https://logo.clearbit.com/dowjones.com")
        )
        val wishlistItems = listOf(
            WishlistModel("Apple Inc.", "AAPL", "https://logo.clearbit.com/apple.com", 1.5),
            WishlistModel("Google LLC", "GOOGL", "https://logo.clearbit.com/google.com", 2.3),
            WishlistModel("Microsoft Corp.", "MSFT", "https://logo.clearbit.com/microsoft.com", 3.1)
        )
        val stockItems = listOf(
            StockModel("Tesla Inc.", "TSLA", "https://logo.clearbit.com/tesla.com", 4.5, 700.0),
            StockModel("Amazon.com Inc.", "AMZN", "https://logo.clearbit.com/amazon.com", 2.8, 3300.0),
            StockModel("Meta Platforms Inc.", "META", "https://logo.clearbit.com/meta.com", 1.2, 250.0)
        )
        binding.indicesRecyclerView.adapter = IndexItemAdapter(indexItems)
        binding.wishlistRecyclerView.adapter = HomeWatchlistItemAdapter(wishlistItems)
        binding.stocksRecyclerView.adapter = StockItemAdapter(stockItems)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}