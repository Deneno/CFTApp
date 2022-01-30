package ya.deneno.cftapp.db.repository

import androidx.lifecycle.LiveData
import ya.deneno.cftapp.model.CurrencyModel

interface CurrencyRepository {
    val allCurrency: LiveData<List<CurrencyModel>>
    suspend fun insertCurrency(currencyModel: CurrencyModel, onSuccess:() -> Unit)
    suspend fun deleteAllCurrency(onSuccess:() -> Unit)
    val getOne: LiveData<CurrencyModel>
}