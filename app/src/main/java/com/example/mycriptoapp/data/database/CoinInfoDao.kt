package com.example.mycriptoapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CoinInfoDao {
    @Query("SELECT * FROM full_price_list ORDER BY lastupdate DESC")
    //fun getPriceList(): LiveData<List<CoinPriceInfo>>
    fun getPriceList(): LiveData<List<CoinInfoDbModel>>

    @Query("SELECT * FROM full_price_list WHERE fromsymbol == :fSym LIMIT 1")
    //fun getPriceInfoAboutCoin(fSym:String): LiveData<CoinPriceInfo>
    fun getPriceInfoAboutCoin(fSym:String): LiveData<CoinInfoDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    //fun insertPriceList(priceList: List<CoinPriceInfo>)
    suspend fun insertPriceList(priceList: List<CoinInfoDbModel>)
}