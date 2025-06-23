package com.example.stockify.network

import com.example.stockify.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiService{

    private val YAHOO_FINANCE_BASE_URL = "https://apidojo-yahoo-finance-v1.p.rapidapi.com/"
    private val YH_FINANCE_BASE_URL = "https://yahoo-finance15.p.rapidapi.com/api/"
    private val X_YH_HOST = "yahoo-finance15.p.rapidapi.com"
    private val X_RAPIDAPI_HOST = "apidojo-yahoo-finance-v1.p.rapidapi.com"
    private val X_RAPIDAPI_KEY = BuildConfig.YAHOO_API_KEY

    val yahooFinanceApiClient: YahooFinanceApiClient by lazy {
        val client: OkHttpClient = createClient(X_RAPIDAPI_HOST, X_RAPIDAPI_KEY)
        createRetrofit(YAHOO_FINANCE_BASE_URL, client)
            .create(YahooFinanceApiClient::class.java)
    }

    val stocksApiClient: StocksApiClient by lazy {
        val client: OkHttpClient = createClient(X_YH_HOST, X_RAPIDAPI_KEY)
        createRetrofit(YH_FINANCE_BASE_URL, client)
            .create(StocksApiClient::class.java)
    }


    private fun createClient(host: String, key: String): OkHttpClient{
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor { chain ->
                val original = chain.request()
                val requestBuilder = original.newBuilder()
                    .header("x-rapidapi-host", host)
                    .header("x-rapidapi-key", key)
                val request = requestBuilder.build()
                chain.proceed(request)
            }
            .build()
    }

    private fun createRetrofit(baseUrl: String, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }



}