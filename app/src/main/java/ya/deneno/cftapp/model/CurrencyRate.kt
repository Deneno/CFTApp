package ya.deneno.cftapp.model

data class CurrencyRate(
    val Date: String,
    val PreviousDate: String,
    val PreviousURL: String,
    val Timestamp: String,
    val Valute: HashMap<String, CurrencyItem>
)