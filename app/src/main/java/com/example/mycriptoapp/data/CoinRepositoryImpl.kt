package com.example.mycriptoapp.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.example.mycriptoapp.data.network.ApiFactory
import com.example.mycriptoapp.data.database.AppDatabase
import com.example.mycriptoapp.data.workers.RefreshDataWorker
import com.example.mycriptoapp.domain.CoinInfo
import com.example.mycriptoapp.domain.CoinRepository
import kotlinx.coroutines.delay

class CoinRepositoryImpl(private val application: Application):CoinRepository {

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

    override fun loadData() {
        val workManager = WorkManager.getInstance(application)
        workManager.enqueueUniqueWork(
            RefreshDataWorker.NAME,
            ExistingWorkPolicy.REPLACE,
            RefreshDataWorker.makeRequest()
        )
    }
}