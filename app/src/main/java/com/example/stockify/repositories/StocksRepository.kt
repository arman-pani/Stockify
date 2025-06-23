package com.example.stockify.repositories

import FinancialDataBody
import StockStatsBody
import com.example.stockify.network.ApiService
import com.example.stockify.network.StocksApiClient

class StocksRepository {
    private val apiService: StocksApiClient = ApiService().stocksApiClient

    interface StockStatsCallback {
        fun onSuccess(response: StockStatsBody)
        fun onError(error: Throwable)
    }

    interface FinancialDataCallback {
        fun onSuccess(response: FinancialDataBody)
        fun onError(error: Throwable)
    }

    suspend fun getStockStats(ticker: String, callback: StockStatsCallback ){
        try {
            val response = apiService.getStockStats(ticker)
            if (response.body != null) {
                callback.onSuccess(response.body)
            } else {
                callback.onError(Throwable("No results found"))
            }
        } catch (e: Exception) {
            callback.onError(e)
        }
    }

    suspend fun getFinancialData(ticker: String, callback: FinancialDataCallback ){
        try{
            val response = apiService.getFinancialData(ticker)
            if (response.body != null) {
                callback.onSuccess(response.body)
            } else {
                callback.onError(Throwable("No results found"))
            }
        }catch (e: Exception) {
            callback.onError(e)
        }
    }
}