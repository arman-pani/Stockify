package com.example.stockify.models


data class StockModel(
    val companyName: String,
    val companySymbol: String,
    val companyLogoUrl: String,
    val percentage: Double,
    val stockPrice: Double
)