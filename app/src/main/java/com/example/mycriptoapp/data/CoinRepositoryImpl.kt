package com.example.mycriptoapp.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.mycriptoapp.data.network.ApiFactory
import com.example.mycriptoapp.data.database.AppDatabase
import com.example.mycriptoapp.domain.CoinInfo
import com.example.mycriptoapp.domain.CoinRepository
import kotlinx.coroutines.delay

class CoinRepositoryImpl(application: Application):CoinRepository {

    private val coinPriceListDao = AppDatabase.getInstance(application).coinPriceInfoDao()
    private val mapper = CoinMapper()

    override fun getCoinInfo(fSym: String): LiveData<CoinInfo> =
        MediatorLiveData<CoinInfo>().apply {
            addSource(coinPriceListDao.getPriceInfoAboutCoin(fSym)) {
                value = mapper.mapDbModelToEntity(it)
            }
        }

    override fun getCoinList(): LiveData<List<CoinInfo>> =
        MediatorLiveData<List<CoinInfo>>().apply {
            addSource(coinPriceListDao.getPriceList()) {
                value = mapper.mapListDbModelToListEntity(it)
            }
        }

    override suspend fun loadData() {

        while (true) {
            try {
                ApiFactory.apiService.getTopCoinsInfo().apply {
                    val top = this.names?.map { it.coinName?.name }?.joinToString(",")
                    if (top != null) {
                        ApiFactory.apiService.getFullPriceList(fSyms = top).apply {
                            val listDto = mapper.mapJsonContainerToListCoinInfoDto(this)
                            coinPriceListDao.insertPriceList(mapper.mapListDtoToListDbModel(listDto))
                        }
                    }
                }
            } catch (e: Exception) {
                TODO("Not yet implemented")
            }
            delay(10000)
        }
    }
}