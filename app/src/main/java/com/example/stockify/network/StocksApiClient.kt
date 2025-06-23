package com.example.stockify.network

import FinancialDataBody
import StockApiResponse
import StockStatsBody
import retrofit2.http.GET
import retrofit2.http.Query


interface StocksApiClient {
    @GET("v1/markets/stock/modules")
    suspend fun getStockStats(
        @Query("ticker") ticker: String,
        @Query("module") module: String = "statistics"
    ): StockApiResponse<StockStatsBody>

    @GET("v1/markets/stock/modules")
    suspend fun getFinancialData(
        @Query("ticker") ticker: String,
        @Query("module") module: String = "financial-data"
    ): StockApiResponse<FinancialDataBody>

}