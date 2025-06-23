package com.example.stockify.utils


enum class FilterType(val title: String, val apiFilter: String) {
    TREADING("Treading", "MOST_WATCHED_TICKERS"),
    TOP_GAINERS("Top Gainers", "DAY_GAINERS"),
    TOP_LOSERS("Top Losers", "DAY_LOSERS"),
    MOST_ACTIVE("Most Active", "MOST_ACTIVES");
}

enum class ProfileTag(val label: String){
    KEY_STATISTICS("Key Stats"),
    FINANCIALS("Financials"),
    NEWS("News")
}