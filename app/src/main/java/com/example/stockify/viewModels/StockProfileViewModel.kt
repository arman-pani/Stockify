package com.example.stockify.viewModels

import FinancialDataBody
import StockStatsBody
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.stockify.models.StockModel
import com.example.stockify.repositories.StocksRepository
import com.example.stockify.room.WatchlistDatabase
import com.example.stockify.room.WatchlistItemDao
import com.example.stockify.room.WatchlistItemModel
import kotlinx.coroutines.launch
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class StockProfileViewModelFactory(
    private val context: Context
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StockProfileViewModel::class.java)) {
            return StockProfileViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

class StockProfileViewModel(context: Context): ViewModel() {

    private val watchlistDao: WatchlistItemDao = WatchlistDatabase.getInstance(context).dao
    private val repository = StocksRepository()

    private var _keyStatics = MutableLiveData<StockStatsBody>()
    private var _financialData = MutableLiveData<FinancialDataBody>()
    private var _ticker: String = ""
    private var _companyName: String = ""
    private var _logoUrl: String = ""
    private var _stockValue: Double = 0.0
    private var _percentChange: Double = 0.0

      var ticker: String
          get() = _ticker
          set(value) { _ticker = value }

      var companyName: String
          get() = _companyName
          set(value) { _companyName = value }

      var logoUrl: String
          get() = _logoUrl
          set(value) { _logoUrl = value }

      var stockValue: Double
          get() = _stockValue
          set(value) { _stockValue = value }

      var percentChange: Double
          get() = _percentChange
          set(value) { _percentChange = value }



    fun setStockDetails(stockModel: StockModel){
        _ticker = stockModel.companySymbol
        _companyName = stockModel.companyName
        _logoUrl = stockModel.companyLogoUrl
        _stockValue = stockModel.stockPrice
        _percentChange = stockModel.percentage

    }


    val keyStatics: LiveData<StockStatsBody> = _keyStatics
    val financialData: LiveData<FinancialDataBody> = _financialData



    fun getFinancialData(ticker: String, callback: StocksRepository.FinancialDataCallback) {
        viewModelScope.launch {
            repository.getFinancialData(ticker, callback)
        }
    }

    fun getStockStats(ticker: String, callback: StocksRepository.StockStatsCallback){
        viewModelScope.launch {
            repository.getStockStats(ticker, callback)
        }
    }

    fun onAddToWatchlist(watchlistItem: WatchlistItemModel){
        viewModelScope.launch {
            watchlistDao.insertWatchlistItem(watchlistItem)
        }
    }
}