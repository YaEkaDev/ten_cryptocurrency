package com.example.mycriptoapp.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.mycriptoapp.R
import com.example.mycriptoapp.databinding.ItemCoinInfoBinding
import com.example.mycriptoapp.domain.CoinInfo
import com.squareup.picasso.Picasso


class CoinInfoAdapter(private val context: Context) : ListAdapter<CoinInfo, CoinInfoViewHolder>(CoinInfoDiffCallback()) {

//    var coinInfoList:List<CoinPriceInfo> = arrayListOf<CoinPriceInfo>()
//        set(value) {
//            field = value
//            notifyDataSetChanged()
//        }
    var onCoinClickListener: OnCoinClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinInfoViewHolder {

       val binding = ItemCoinInfoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)

        return CoinInfoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CoinInfoViewHolder, position: Int) {
        val coin = getItem(position)
        with(holder) {
          //  binding.tvSymbs.text = String.format(context.resources.getString(R.string.symbs_template), coin.fromsymbol,coin.tosymbol)
           // binding.tvCourse.text = coin.price.toString()
            binding.tvLastDate.text = String.format(context.resources.getString(R.string.last_update_template), coin.lastupdate)
            Picasso.get().load(coin.imageurl).into(binding.ivLogoCoin)
            itemView.setOnClickListener { onCoinClickListener?.onCoinClick(coin) }
        }
    }


    interface OnCoinClickListener{
        fun onCoinClick(coinPriceInfo: CoinInfo)
    }
}