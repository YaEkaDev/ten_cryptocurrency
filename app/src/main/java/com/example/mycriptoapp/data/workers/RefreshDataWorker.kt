package com.example.mycriptoapp.data.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.mycriptoapp.data.CoinMapper
import com.example.mycriptoapp.data.database.AppDatabase
import com.example.mycriptoapp.data.network.ApiFactory
import kotlinx.coroutines.delay

class RefreshDataWorker(
    context: Context,
    workerParameters: WorkerParameters
): CoroutineWorker(context, workerParameters) {

    private val coinPriceListDao = AppDatabase.getInstance(context).coinPriceInfoDao()
    private val mapper = CoinMapper()
    override suspend fun doWork(): Result {

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
        return Result.success()
    }

    companion object{
        const val NAME = "RefreshDataWorker"

        fun makeRequest(): OneTimeWorkRequest{
            return OneTimeWorkRequestBuilder<RefreshDataWorker>().build()
        }
    }
}