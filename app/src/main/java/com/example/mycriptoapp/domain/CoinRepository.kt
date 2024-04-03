package com.example.mycriptoapp.domain

import androidx.lifecycle.LiveData

interface CoinRepository {
    fun getCoinInfo(fSym: String): LiveData<CoinInfo>

    fun getCoinList(): LiveData<List<CoinInfo>>

    suspend fun loadData()

}