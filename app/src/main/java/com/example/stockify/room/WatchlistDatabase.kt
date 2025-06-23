package com.example.stockify.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [WatchlistItemModel::class],
    version = 1
)
abstract class WatchlistDatabase: RoomDatabase() {
    abstract val dao: WatchlistItemDao

    companion object {
        @Volatile
        private var INSTANCE: WatchlistDatabase? = null

        fun getInstance(context: Context): WatchlistDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WatchlistDatabase::class.java,
                    "watchlist_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}