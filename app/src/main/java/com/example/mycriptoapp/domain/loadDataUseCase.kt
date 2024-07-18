package com.example.mycriptoapp.domain

class loadDataUseCase(private val repository: CoinRepository) {

    operator fun invoke(){
        repository.loadData()
    }

}