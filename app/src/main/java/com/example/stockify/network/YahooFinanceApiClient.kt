package com.example.stockify.network

import MarketSummaryResponse
import com.example.stockify.models.FinanceResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface YahooFinanceApiClient{
    @GET("screeners/get-symbols-by-predefined")
    suspend fun getSymbolsByPredefined(
        @Query("scrIds") scrIds: String,
        @Query("start") start: String,
        @Query("count") count: String
    ): FinanceResponse

    @GET("market/v2/get-summary")
    suspend fun getMarketSummary(
        @Query("region") region: String = "US",
    ): MarketSummaryResponse
}

