package ya.deneno.cftapp.api

import retrofit2.Response
import retrofit2.http.GET
import ya.deneno.cftapp.model.CurrencyRate


interface ApiService {
    @GET("daily_json.js")
    suspend fun getCurrencyRateFromApi(): Response<CurrencyRate>
}