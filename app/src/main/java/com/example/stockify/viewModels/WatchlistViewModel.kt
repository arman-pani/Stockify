package com.example.stockify.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stockify.room.WatchlistItemDao
import com.example.stockify.room.WatchlistItemModel
import kotlinx.coroutines.launch

class WatchlistViewModel(
        private val dao: WatchlistItemDao
): ViewModel() {

    private var _watchlist = MutableLiveData<List<WatchlistItemModel>>(emptyList())

    val getWatchlist: LiveData<List<WatchlistItemModel>> = _watchlist

    fun onDeleteItem(position: Int) {
        viewModelScope.launch {
            val item = _watchlist.value?.get(position)
            if (item != null) {
                dao.deleteWatchlistItem(item)
            }
            loadWatchlistItems()
        }
    }

    fun loadWatchlistItems(){
        viewModelScope.launch {
            val watchlist: LiveData<List<WatchlistItemModel>> = dao.getAllWatchlistItems()
            _watchlist.postValue(watchlist.value)
        }
    }
}