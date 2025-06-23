package com.example.stockify.models

import com.google.gson.annotations.SerializedName


data class FinanceResponse (
    @SerializedName("finance")
    val finance: FinanceResult
)

data class FinanceResult(
    @SerializedName("result")
    val result: List<FinanceResultItem>
)

data class FinanceResultItem(
    @SerializedName("title")
    val title: String,
    @SerializedName("canonicalName")
    val canonicalName: String,
    @SerializedName("quotes")
    val quotes: List<Quote>
)

data class Quote(
    @SerializedName("symbol")
    val symbol: String,
    @SerializedName("longName")
    val longName: String?,
    @SerializedName("regularMarketPrice")
    val regularMarketPrice: PriceData?,
    @SerializedName("regularMarketChangePercent")
    val regularMarketChangePercent: PriceData?,
    @SerializedName("shortName")
    val shortName: String?,
    @SerializedName("marketCap")
    val marketCap: PriceData?,
    @SerializedName("displayName")
    val displayName: String?,
    @SerializedName("regularMarketDayHigh")
    val regularMarketDayHigh: PriceData?,
    @SerializedName("regularMarketDayLow")
    val regularMarketDayLow: PriceData?
)

data class PriceData(
    val raw: Double?,
    val fmt: String?
)