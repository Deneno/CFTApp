package ya.deneno.cftapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "currency_table")
data class CurrencyModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo
    val CharCode: String,
    @ColumnInfo
    val Name: String,
    @ColumnInfo
    val Nominal: Int,
    @ColumnInfo
    val Previous: Double,
    @ColumnInfo
    val Value: Double,
    @ColumnInfo
    val Date: String
) : Serializable