package ya.deneno.cftapp.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import ya.deneno.cftapp.model.CurrencyModel

@Dao
interface CurrencyDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(currencyModel: CurrencyModel)

    @Query("delete from currency_table")
    suspend fun deleteAllCurrency()

    @Query("select * from currency_table limit 1")
    fun getOne(): LiveData<CurrencyModel>

    @Query("select * from currency_table")
    fun getAllCurrency(): LiveData<List<CurrencyModel>>

}