package ya.deneno.cftapp.db.repository

import androidx.lifecycle.LiveData
import ya.deneno.cftapp.db.dao.CurrencyDao
import ya.deneno.cftapp.model.CurrencyModel

class CurrencyRealization(private val currencyDao: CurrencyDao): CurrencyRepository {

    override val allCurrency: LiveData<List<CurrencyModel>>
        get() = currencyDao.getAllCurrency()

    override suspend fun insertCurrency(currencyModel: CurrencyModel, onSuccess: () -> Unit) {
        currencyDao.insert(currencyModel)
        onSuccess()
    }

    override suspend fun deleteAllCurrency(onSuccess: () -> Unit) {
        currencyDao.deleteAllCurrency()
        onSuccess()
    }

    override val getOne: LiveData<CurrencyModel>
        get() = currencyDao.getOne()

}