package com.example.stockify.viewModels

import MarketItem
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stockify.models.IndexModel
import com.example.stockify.models.Quote
import com.example.stockify.models.StockModel
import com.example.stockify.room.WatchlistItemModel
import com.example.stockify.repositories.FinanceRepository
import com.example.stockify.utils.FilterType
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel(){
    private val financeRepository = FinanceRepository()

    private val _indices = MutableLiveData<List<IndexModel>>()
    private val _watchlist = MutableLiveData<List<WatchlistItemModel>>()
    private val _stocks = MutableLiveData<List<StockModel>>()

    val indices: LiveData<List<IndexModel>> = _indices
    val watchlist: LiveData<List<WatchlistItemModel>> = _watchlist
    val stocks: LiveData<List<StockModel>> = _stocks

    var currentStockFilter: FilterType = FilterType.TREADING
        set(value) {
            field = value
            loadStocks(value.apiFilter)
        }

    fun loadStocks(filter: String) {
        viewModelScope.launch {
            financeRepository.getSymbolsByPredefined(filter, object : FinanceRepository.FinanceCallback {
                override fun onSuccess(response: List<Quote>) {
                    val stockList = response.map {
                        StockModel(
                            companyName = it.displayName ?: it.longName ?: it.shortName ?: "Unknown",
                            companySymbol = it.symbol,
                            companyLogoUrl = "https://logo.clearbit.com/${it.symbol.lowercase()}.com",
                            percentage = it.regularMarketChangePercent?.raw ?: 0.0,
                            stockPrice = it.regularMarketPrice?.raw ?: 0.0
                        )
                    }
                    _stocks.postValue(stockList)
                }

                override fun onError(error: Throwable) {
                    Log.e("HomeViewModel", "Error loading stock data", error)
                }
            })
        }
    }

    fun loadIndicesData() {
        viewModelScope.launch {
            financeRepository.getMarketSummary(object : FinanceRepository.MarketSummaryCallback {
                override fun onSuccess(response: List<MarketItem>) {
                    val indexList = response.map {
                        IndexModel(
                            indexName = it.shortName,
                            indexLogoUrl = it.symbol,
                            indexValue = String.format("%.2f", (it.regularMarketPrice.raw)).toDouble(),
                            percentageChange = String.format("%.2f", ((it.regularMarketPreviousClose.raw - it.regularMarketPrice.raw) / it.regularMarketPreviousClose.raw * 100)).toDouble(),
                            valueChange = String.format("%.2f", (it.regularMarketPreviousClose.raw - it.regularMarketPrice.raw)).toDouble()
                        )
                    }
                    _indices.postValue(indexList)
                }

                override fun onError(error: Throwable) {
                    Log.e("HomeViewModel", "Error loading index data", error)
                }
            })
        }
    }

    fun loadWatchlistData() {
        // Dummy data; replace with DB/Network source as needed
        val dummyWatchlist = listOf(
            WatchlistItemModel(1,"Apple Inc.", "AAPL", "https://logo.clearbit.com/apple.com", 1.5, 10.2),
            WatchlistItemModel(2,"Google LLC", "GOOGL", "https://logo.clearbit.com/google.com", 2.3, 20.3),
            WatchlistItemModel(3,"Microsoft Corp.", "MSFT", "https://logo.clearbit.com/microsoft.com", 3.1, 25.8)
        )
        _watchlist.postValue(dummyWatchlist)
    }

    fun loadHomePageData() {
        loadStocks(FilterType.TREADING.apiFilter)
        loadIndicesData()
        loadWatchlistData()
    }

}