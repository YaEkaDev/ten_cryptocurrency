package com.example.mycriptoapp.domain

import androidx.lifecycle.LiveData

class GetCoinListUseCase(private val repository: CoinRepository) {
    operator fun invoke():LiveData<List<CoinInfo>>{
        return repository.getCoinList()
    }
}