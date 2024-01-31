package com.example.mycriptoapp.api

import androidx.constraintlayout.motion.widget.Key
import com.example.mycriptoapp.pojo.CoinInfoListOfData
import com.example.mycriptoapp.pojo.CoinPriceInfoRawData
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("top/totalvolfull")
    fun getTopCoinsInfo(
        @Query(QUERY_PARAM_API_KEY) apiKey: String = "45b0e8b2f439e06afcfcb749ad917074c20fc977e45709aa3b3f1b81901f14a2",
        @Query(QUERY_PARAM_LIMIT) limit: Int = 10,
        @Query(QUERY_PARAM_TO_SYMBOL) tSym: String = CURRENCY
    ): Single<CoinInfoListOfData>

    @GET("pricemultifull")
    fun getFullPriceList(
        @Query(QUERY_PARAM_API_KEY) apiKey: String = "45b0e8b2f439e06afcfcb749ad917074c20fc977e45709aa3b3f1b81901f14a2",
        @Query(QUERY_PARAM_FROM_SYMBOLS) fSyms: String,
        @Query(QUERY_PARAM_TO_SYMBOLS) tSyms: String = CURRENCY
    ): Single<CoinPriceInfoRawData>

    companion object{
        private const val QUERY_PARAM_LIMIT = "limit"
        private const val QUERY_PARAM_TO_SYMBOL = "tsym"
        private const val QUERY_PARAM_TO_SYMBOLS = "tsyms"
        private const val QUERY_PARAM_FROM_SYMBOLS = "fsyms"
        private const val QUERY_PARAM_API_KEY = "api_key"
        private const val CURRENCY = "USD"
    }
}