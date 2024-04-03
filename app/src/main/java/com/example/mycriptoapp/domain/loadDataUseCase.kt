package com.example.mycriptoapp.domain

class loadDataUseCase(private val repository: CoinRepository) {

    operator suspend fun invoke(){
        repository.loadData()
    }

}