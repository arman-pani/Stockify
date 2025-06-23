package com.example.stockify.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.stockify.R
import com.example.stockify.adapters.*
import com.example.stockify.databinding.FragmentHomeBinding
import com.example.stockify.models.*
import com.example.stockify.room.WatchlistItemModel
import com.example.stockify.utils.FilterType
import com.example.stockify.viewModels.HomeViewModel
import com.example.stockify.viewModels.StockProfileViewModel
import com.example.stockify.viewModels.StockProfileViewModelFactory

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var stockViewModel : StockProfileViewModel

    // Temporary data holders
    private var indicesData: List<IndexModel>? = null
    private var watchlistData: List<WatchlistItemModel>? = null
    private var stocksData: List<StockModel>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        val factory = StockProfileViewModelFactory(requireContext().applicationContext)
        stockViewModel = ViewModelProvider(this, factory)[StockProfileViewModel::class.java]

        setupObservers()
        showLoading(true)
        viewModel.loadHomePageData()

        return binding.root
    }

    private fun setupObservers() {
        viewModel.indices.observe(viewLifecycleOwner) { indices ->
            indicesData = indices
            tryBuildHomeAdapter()
        }

        viewModel.watchlist.observe(viewLifecycleOwner) { watchlist ->
            watchlistData = watchlist
            tryBuildHomeAdapter()
        }

        viewModel.stocks.observe(viewLifecycleOwner) { stocks ->
            stocksData = stocks
            tryBuildHomeAdapter()
        }
    }

    private fun tryBuildHomeAdapter() {
        if (indicesData != null && watchlistData != null && stocksData != null) {

            val homeItems = listOf(
                HomeItem.SearchBar,

                HomeItem.SectionHeader("Major Indices", R.drawable.indices),
                HomeItem.HorizontalList(ListType.INDICES, indicesData!!),

                HomeItem.SectionHeader("Watchlist", R.drawable.watchlist),
                HomeItem.HorizontalList(ListType.WATCHLIST, watchlistData!!),

                HomeItem.SectionHeader("Stocks", R.drawable.stocks),
                HomeItem.HorizontalList(
                    ListType.FILTER,
                    listOf(
                        FilterType.TREADING,
                        FilterType.TOP_GAINERS,
                        FilterType.TOP_LOSERS,
                        FilterType.MOST_ACTIVE
                    )
                ),
                HomeItem.VerticalList(stocksData!!)
            )

            binding.homeRecyclerView.apply {
                layoutManager = LinearLayoutManager(requireContext())
               adapter = HomeAdapter(
                homeItems,
                onFilterSelected = { filter: FilterType ->
                    viewModel.currentStockFilter = filter
                },
                onStockItemTapListener = { stock: StockModel ->
                    stockViewModel.setStockDetails(stock)
                }
            )
            }

            showLoading(false)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        binding.homeRecyclerView.visibility = if (isLoading) View.GONE else View.VISIBLE
    }
}
