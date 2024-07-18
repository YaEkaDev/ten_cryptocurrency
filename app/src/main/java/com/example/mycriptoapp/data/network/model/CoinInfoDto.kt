package com.example.mycriptoapp.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CoinInfoDto(
    @SerializedName("FROMSYMBOL")
    @Expose
    val fromsymbol: String = "",

    @SerializedName("TOSYMBOL")
    @Expose
    val tosymbol: String? = null,

    @SerializedName("LASTMARKET")
    @Expose
    val lastmarket: String? = null,

    @SerializedName("PRICE")
    @Expose
    val price: Double? = null,

    @SerializedName("LASTUPDATE")
    @Expose
    val lastupdate: Long? = null,

    @SerializedName("HIGHDAY")
    @Expose
    val highday: Double? = null,

    @SerializedName("LOWDAY")
    @Expose
    val lowday: Double? = null,

    @SerializedName("IMAGEURL")
    @Expose
    val imageurl: String? = null
)


