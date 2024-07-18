package com.example.mycriptoapp.data

import com.example.mycriptoapp.data.network.ApiFactory
import com.example.mycriptoapp.data.network.model.CoinInfoDto
import com.example.mycriptoapp.data.database.CoinInfoDbModel
import com.example.mycriptoapp.domain.CoinInfo
import com.example.mycriptoapp.data.network.model.CoinInfoJsonContainerDto
import com.google.gson.Gson
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

class CoinMapper {

    fun mapDbModelToEntity(coin: CoinInfoDbModel) = CoinInfo(
        fromsymbol = coin.fromsymbol,
        tosymbol = coin.tosymbol,
        lastmarket = coin.lastmarket,
        price = coin.price,
        lastupdate = coin.lastupdate,
        highday = coin.highday,
        lowday = coin.lowday,
        imageurl = coin.imageurl
    )

    fun mapDtoToDbModel(coin: CoinInfoDto) = CoinInfoDbModel(
        fromsymbol = coin.fromsymbol,
        tosymbol = coin.tosymbol,
        lastmarket = coin.lastmarket,
        price = coin.price,
        lastupdate = convertTimeStampToTime(coin.lastupdate),
        highday = coin.highday,
        lowday = coin.lowday,
        imageurl = getFullImageUrl(coin.imageurl)
    )

    //при загрузке данных из сети из сущности в модель для записи в БД
    fun mapListDtoToListDbModel(list: List<CoinInfoDto>) = list.map {
        mapDtoToDbModel(it)
    }

    //получение данных из БД
    fun mapListDbModelToListEntity(list: List<CoinInfoDbModel>) = list.map{
        mapDbModelToEntity(it)
    }

    private fun getFullImageUrl(imageurl: String?):String {
        return ApiFactory.BASE_IMAGE_URL + imageurl
    }

    private fun convertTimeStampToTime(timeStamp: Long?):String {
        if (timeStamp == null) return ""
        val stamp = Timestamp(timeStamp * 1000)
        val date = Date(stamp.time)
        val pattern = "HH:mm:ss"
        val sdf = SimpleDateFormat(pattern, Locale.getDefault())
        sdf.timeZone = TimeZone.getDefault()
        return sdf.format(date)
    }

    fun mapJsonContainerToListCoinInfoDto(jsonContainer: CoinInfoJsonContainerDto): List<CoinInfoDto>{
        val result = mutableListOf<CoinInfoDto>()
        val jsonObject = jsonContainer.json ?: return result

        val coinKeySet = jsonObject.keySet()
        for (coinKey in coinKeySet){
            val currencyJson = jsonObject.getAsJsonObject(coinKey)
            val currencyKeySet = currencyJson.keySet()
            for (currencyKey in currencyKeySet){
                val priceInfo = Gson().fromJson(currencyJson.getAsJsonObject(currencyKey), CoinInfoDto::class.java)
                result.add(priceInfo)
            }

        }
        return result
    }
}