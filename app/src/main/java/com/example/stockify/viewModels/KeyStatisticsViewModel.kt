package com.example.stockify.viewModels

import StockStatsBody
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.stockify.repositories.StocksRepository

class KeyStatisticsViewModel: ViewModel()  {
    private val repository = StocksRepository()

    private var _keyStatics = MutableLiveData<StockStatsBody>()

    val keyStatics: LiveData<StockStatsBody> = _keyStatics


}