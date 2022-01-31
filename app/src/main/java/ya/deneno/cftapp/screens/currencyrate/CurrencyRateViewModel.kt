package ya.deneno.cftapp.screens.currencyrate

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import ya.deneno.cftapp.APP
import ya.deneno.cftapp.REPOSITORY
import ya.deneno.cftapp.api.RetrofitInstance
import ya.deneno.cftapp.db.CurrencyDatabase
import ya.deneno.cftapp.db.repository.CurrencyRealization
import ya.deneno.cftapp.model.CurrencyModel

class CurrencyRateViewModel: ViewModel() {
    private var job: Job = viewModelScope.launch {}
    fun syncCurrencyRate(): LiveData<List<CurrencyModel>> {
        job.cancel()
        job = viewModelScope.launch {
            REPOSITORY.deleteAllCurrency{}
            val response = RetrofitInstance.api.getCurrencyRateFromApi().body()
            val map = response?.Valute
            if (map != null) {
                for ((_, currency) in map) {
                    REPOSITORY.insertCurrency(
                        CurrencyModel(
                            CharCode = currency.CharCode,
                            Name = currency.Name,
                            Nominal = currency.Nominal,
                            Previous = currency.Previous,
                            Value = currency.Value,
                            Date = response.Timestamp
                        )
                    ){}

                }
            }
        }
        Toast.makeText(APP, "Курсы валют обновлены", Toast.LENGTH_SHORT).show()
        return REPOSITORY.allCurrency
    }

    fun initDataBase() {
        val currencyDao = CurrencyDatabase.getInstance(APP).getCurrencyDao()
        REPOSITORY = CurrencyRealization(currencyDao)
    }

    fun getAllCurrency(): LiveData<List<CurrencyModel>> {
        return REPOSITORY.allCurrency
    }

    fun getOne(): LiveData<CurrencyModel> {
        return REPOSITORY.getOne
    }
}