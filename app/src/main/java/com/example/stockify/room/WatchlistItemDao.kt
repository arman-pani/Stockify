package com.example.stockify.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WatchlistItemDao {

    @Insert
    suspend fun insertWatchlistItem(watchlistItem: WatchlistItemModel)

    @Delete
    suspend fun deleteWatchlistItem(watchlistItem: WatchlistItemModel)

    @Query("SELECT * FROM Watchlist")
    fun getAllWatchlistItems(): LiveData<List<WatchlistItemModel>>

}