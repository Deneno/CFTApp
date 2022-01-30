package ya.deneno.cftapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ya.deneno.cftapp.db.dao.CurrencyDao
import ya.deneno.cftapp.model.CurrencyModel

@Database(entities = [CurrencyModel::class], version = 1)
abstract class CurrencyDatabase: RoomDatabase() {
    abstract fun getCurrencyDao(): CurrencyDao

    companion object {
        private var database: CurrencyDatabase?= null

        @Synchronized
        fun getInstance(context: Context): CurrencyDatabase {
            return if (database == null) {
                database = Room.databaseBuilder(context, CurrencyDatabase::class.java, "db" ).build()
                database as CurrencyDatabase
            } else {
                database as CurrencyDatabase
            }
        }
    }
}