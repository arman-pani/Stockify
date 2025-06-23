package com.example.stockify.room

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName="Watchlist")
data class WatchlistItemModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val companyName: String,
    val companySymbol: String,
    val companyLogo: String,
    val stockValue: Double,
    val percentage: Double
)