package com.example.mycriptoapp.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mycriptoapp.data.CoinRepositoryImpl
import com.example.mycriptoapp.domain.CoinInfo
import com.example.mycriptoapp.domain.GetCoinInfoUseCase
import com.example.mycriptoapp.domain.GetCoinListUseCase
import com.example.mycriptoapp.domain.loadDataUseCase
import kotlinx.coroutines.launch

class CoinViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = CoinRepositoryImpl(application)

    private val getCoinInfoUseCase = GetCoinInfoUseCase(repository)
    private val getCoinListUseCase = GetCoinListUseCase(repository)
    private val loadDataUseCase = loadDataUseCase(repository)

    val coinInfoList = getCoinListUseCase.invoke()

    init {
        viewModelScope.launch {
            loadDataUseCase.invoke()
        }
    }

    fun getDetailInfo(fSym: String) = getCoinInfoUseCase.invoke(fSym)

}