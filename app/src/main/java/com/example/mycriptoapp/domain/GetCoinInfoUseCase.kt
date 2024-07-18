package com.example.mycriptoapp.domain

import androidx.lifecycle.LiveData

class GetCoinInfoUseCase(private val repository: CoinRepository) {

    operator fun invoke(fSym: String) : LiveData<CoinInfo> {
        return repository.getCoinInfo(fSym)
    }

}