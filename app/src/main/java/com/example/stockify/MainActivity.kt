package com.example.stockify

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val wishlistRecyclerView = findViewById<RecyclerView>(R.id.wishlistRecyclerView)
        val stocksRecyclerView = findViewById<RecyclerView>(R.id.stocksRecyclerView)

        wishlistRecyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        stocksRecyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        val wishlistItems = listOf(
            WishlistModel("Apple Inc.", "AAPL", "https://logo.clearbit.com/apple.com", 1.5),
            WishlistModel("Google LLC", "GOOGL", "https://logo.clearbit.com/google.com", 2.3),
            WishlistModel("Microsoft Corp.", "MSFT", "https://logo.clearbit.com/microsoft.com", 3.1)
        )
        val stockItems = listOf(
            StockModel("Tesla Inc.", "TSLA", "https://logo.clearbit.com/tesla.com", 4.5, 700.0),
            StockModel("Amazon.com Inc.", "AMZN", "https://logo.clearbit.com/amazon.com", 2.8, 3300.0),
            StockModel("Meta Platforms Inc.", "META", "https://logo.clearbit.com/meta.com", 1.2, 250.0)
        )
        wishlistRecyclerView.adapter = WishlistItemAdapter(wishlistItems)
        stocksRecyclerView.adapter = StockItemAdapter(stockItems)
    }
}