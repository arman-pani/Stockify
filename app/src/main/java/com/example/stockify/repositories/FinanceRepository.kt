package com.example.stockify.repositories

import MarketItem
import MarketSummaryResponse
import com.example.stockify.models.Quote
import com.example.stockify.network.ApiService
import com.example.stockify.network.YahooFinanceApiClient

class FinanceRepository {
    private val apiService: YahooFinanceApiClient = ApiService().yahooFinanceApiClient

    interface FinanceCallback {
        fun onSuccess(response: List<Quote>)
        fun onError(error: Throwable)
    }

    interface MarketSummaryCallback {
        fun onSuccess(response: List<MarketItem>)
        fun onError(error: Throwable)
    }
    suspend fun getSymbolsByPredefined(scrIds: String, callback: FinanceCallback){
        try {
            val response = apiService.getSymbolsByPredefined(scrIds, "0", "10")
            if (response.finance.result.isNotEmpty()) {
                callback.onSuccess(response.finance.result[0].quotes)
            } else {
                callback.onError(Throwable("No results found"))
            }
        } catch (e: Exception) {
            callback.onError(e)
        }
    }

    suspend fun getMarketSummary(callback: MarketSummaryCallback){
        try {
            val response = apiService.getMarketSummary()
            if (response.marketSummaryAndSparkResponse.result.isNotEmpty()) {
                callback.onSuccess(response.marketSummaryAndSparkResponse.result)
            } else {
                callback.onError(Throwable("No results found"))
            }
        } catch (e: Exception) {
            callback.onError(e)
        }
    }


}