import com.google.gson.annotations.SerializedName

data class MarketSummaryResponse(
    @SerializedName("marketSummaryAndSparkResponse")
    val marketSummaryAndSparkResponse: MarketSummaryAndSparkResponse
)

data class MarketSummaryAndSparkResponse(
    @SerializedName("result")
    val result: List<MarketItem>,

    @SerializedName("error")
    val error: Any?
)

data class MarketItem(
    @SerializedName("fullExchangeName")
    val fullExchangeName: String,

    @SerializedName("symbol")
    val symbol: String,

    @SerializedName("quoteType")
    val quoteType: String,

    @SerializedName("spark")
    val spark: SparkData,

    @SerializedName("exchange")
    val exchange: String,

    @SerializedName("exchangeDataDelayedBy")
    val exchangeDataDelayedBy: Int,

    @SerializedName("exchangeTimezoneName")
    val exchangeTimezoneName: String,

    @SerializedName("exchangeTimezoneShortName")
    val exchangeTimezoneShortName: String,

    @SerializedName("firstTradeDateMilliseconds")
    val firstTradeDateMilliseconds: Long,

    @SerializedName("hasPrePostMarketData")
    val hasPrePostMarketData: Boolean,

    @SerializedName("marketState")
    val marketState: String,

    @SerializedName("market")
    val market: String,

    @SerializedName("priceHint")
    val priceHint: Int,

    @SerializedName("region")
    val region: String,

    @SerializedName("shortName")
    val shortName: String,

    @SerializedName("sourceInterval")
    val sourceInterval: Int,

    @SerializedName("triggerable")
    val triggerable: Boolean,

    @SerializedName("regularMarketTime")
    val regularMarketTime: MarketTime,

    @SerializedName("regularMarketPrice")
    val regularMarketPrice: MarketPrice,

    @SerializedName("regularMarketPreviousClose")
    val regularMarketPreviousClose: MarketPrice,

    @SerializedName("customPriceAlertConfidence")
    val customPriceAlertConfidence: String
)

data class MarketTime(
    @SerializedName("raw")
    val raw: Long,

    @SerializedName("fmt")
    val fmt: String
)

data class MarketPrice(
    @SerializedName("raw")
    val raw: Double,

    @SerializedName("fmt")
    val fmt: String
)

data class SparkData(
    @SerializedName("symbol")
    val symbol: String,

    @SerializedName("start")
    val start: Long,

    @SerializedName("end")
    val end: Long,

    @SerializedName("timestamp")
    val timestamp: List<Long>,

    @SerializedName("close")
    val close: List<Double>,

    @SerializedName("dataGranularity")
    val dataGranularity: Int,

    @SerializedName("chartPreviousClose")
    val chartPreviousClose: Double,

    @SerializedName("previousClose")
    val previousClose: Double
)

