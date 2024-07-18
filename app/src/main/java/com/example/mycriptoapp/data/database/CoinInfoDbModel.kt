package com.example.mycriptoapp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "full_price_list")
data class CoinInfoDbModel(

    @PrimaryKey
    val fromsymbol: String = "",

    val tosymbol: String? = null,

    val lastmarket: String? = null,

    val price: Double? = null,

    val lastupdate: String? = null,

    val highday: Double? = null,

    val lowday: Double? = null,

    val imageurl: String? = null
)
