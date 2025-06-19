package com.example.stockify.models

data class IndexModel(
    val indexName: String,
    val indexValue: Double,
    val valueChange: Double,
    val percentageChange: Double,
    val indexLogoUrl: String,

    ) {
}