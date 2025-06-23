import com.google.gson.annotations.SerializedName

data class StockApiResponse<T>(
    @SerializedName("meta") val meta: Meta?,
    @SerializedName("body") val body: T?
)

data class Meta(
    @SerializedName("version") val version: String?,
    @SerializedName("status") val status: Int?,
    @SerializedName("copywrite") val copywrite: String?,
    @SerializedName("symbol") val symbol: String?,
    @SerializedName("processedTime") val processedTime: String?,
    @SerializedName("modules") val modules: String?
)

data class StockStatsBody(
    @SerializedName("maxAge") val maxAge: Int?,
    @SerializedName("priceHint") val priceHint: ValueDetail?,
    @SerializedName("enterpriseValue") val enterpriseValue: ValueDetail?,
    @SerializedName("forwardPE") val forwardPE: ValueDetail?,
    @SerializedName("profitMargins") val profitMargins: ValueDetail?,
    @SerializedName("floatShares") val floatShares: ValueDetail?,
    @SerializedName("sharesOutstanding") val sharesOutstanding: ValueDetail?,
    @SerializedName("sharesShort") val sharesShort: ValueDetail?,
    @SerializedName("sharesShortPriorMonth") val sharesShortPriorMonth: ValueDetail?,
    @SerializedName("sharesShortPreviousMonthDate") val sharesShortPreviousMonthDate: ValueDate?,
    @SerializedName("dateShortInterest") val dateShortInterest: ValueDate?,
    @SerializedName("sharesPercentSharesOut") val sharesPercentSharesOut: ValueDetail?,
    @SerializedName("heldPercentInsiders") val heldPercentInsiders: ValueDetail?,
    @SerializedName("heldPercentInstitutions") val heldPercentInstitutions: ValueDetail?,
    @SerializedName("shortRatio") val shortRatio: ValueDetail?,
    @SerializedName("shortPercentOfFloat") val shortPercentOfFloat: ValueDetail?,
    @SerializedName("beta") val beta: ValueDetail?,
    @SerializedName("impliedSharesOutstanding") val impliedSharesOutstanding: ValueDetail?,
    @SerializedName("bookValue") val bookValue: ValueDetail?,
    @SerializedName("priceToBook") val priceToBook: ValueDetail?,
    @SerializedName("lastFiscalYearEnd") val lastFiscalYearEnd: ValueDate?,
    @SerializedName("nextFiscalYearEnd") val nextFiscalYearEnd: ValueDate?,
    @SerializedName("mostRecentQuarter") val mostRecentQuarter: ValueDate?,
    @SerializedName("earningsQuarterlyGrowth") val earningsQuarterlyGrowth: ValueDetail?,
    @SerializedName("netIncomeToCommon") val netIncomeToCommon: ValueDetail?,
    @SerializedName("trailingEps") val trailingEps: ValueDetail?,
    @SerializedName("forwardEps") val forwardEps: ValueDetail?,
    @SerializedName("lastSplitFactor") val lastSplitFactor: String?,
    @SerializedName("lastSplitDate") val lastSplitDate: ValueDate?,
    @SerializedName("enterpriseToRevenue") val enterpriseToRevenue: ValueDetail?,
    @SerializedName("enterpriseToEbitda") val enterpriseToEbitda: ValueDetail?,
    @SerializedName("52WeekChange") val `52WeekChange`: ValueDetail?,
    @SerializedName("sandP52WeekChange") val sandP52WeekChange: ValueDetail?,
    @SerializedName("lastDividendValue") val lastDividendValue: ValueDetail?,
    @SerializedName("lastDividendDate") val lastDividendDate: ValueDate?
)

data class FinancialDataBody(
    @SerializedName("maxAge") val maxAge: Int?,
    @SerializedName("currentPrice") val currentPrice: ValueDetail?,
    @SerializedName("targetHighPrice") val targetHighPrice: ValueDetail?,
    @SerializedName("targetLowPrice") val targetLowPrice: ValueDetail?,
    @SerializedName("targetMeanPrice") val targetMeanPrice: ValueDetail?,
    @SerializedName("targetMedianPrice") val targetMedianPrice: ValueDetail?,
    @SerializedName("recommendationMean") val recommendationMean: ValueDetail?,
    @SerializedName("recommendationKey") val recommendationKey: String?,
    @SerializedName("numberOfAnalystOpinions") val numberOfAnalystOpinions: ValueDetail?,
    @SerializedName("totalCash") val totalCash: ValueDetail?,
    @SerializedName("totalCashPerShare") val totalCashPerShare: ValueDetail?,
    @SerializedName("ebitda") val ebitda: ValueDetail?,
    @SerializedName("totalDebt") val totalDebt: ValueDetail?,
    @SerializedName("quickRatio") val quickRatio: ValueDetail?,
    @SerializedName("currentRatio") val currentRatio: ValueDetail?,
    @SerializedName("totalRevenue") val totalRevenue: ValueDetail?,
    @SerializedName("debtToEquity") val debtToEquity: ValueDetail?,
    @SerializedName("revenuePerShare") val revenuePerShare: ValueDetail?,
    @SerializedName("returnOnAssets") val returnOnAssets: ValueDetail?,
    @SerializedName("returnOnEquity") val returnOnEquity: ValueDetail?,
    @SerializedName("grossProfits") val grossProfits: ValueDetail?,
    @SerializedName("freeCashflow") val freeCashflow: ValueDetail?,
    @SerializedName("operatingCashflow") val operatingCashflow: ValueDetail?,
    @SerializedName("earningsGrowth") val earningsGrowth: ValueDetail?,
    @SerializedName("revenueGrowth") val revenueGrowth: ValueDetail?,
    @SerializedName("grossMargins") val grossMargins: ValueDetail?,
    @SerializedName("ebitdaMargins") val ebitdaMargins: ValueDetail?,
    @SerializedName("operatingMargins") val operatingMargins: ValueDetail?,
    @SerializedName("profitMargins") val profitMargins: ValueDetail?,
    @SerializedName("financialCurrency") val financialCurrency: String?
)

data class ValueDetail(
    @SerializedName("raw") val raw: Double?,
    @SerializedName("fmt") val fmt: String?,
    @SerializedName("longFmt") val longFmt: String? = null
)

data class ValueDate(
    @SerializedName("raw") val raw: Long?,
    @SerializedName("fmt") val fmt: String?
)

