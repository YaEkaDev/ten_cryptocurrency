package com.example.mycriptoapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mycriptoapp.R
import com.example.mycriptoapp.databinding.ItemCoinInfoBinding
import com.example.mycriptoapp.pojo.CoinPriceInfo
import com.squareup.picasso.Picasso


class CoinInfoAdapter(private val context: Context) : RecyclerView.Adapter<CoinInfoAdapter.CoinInfoViewHolder>() {

    lateinit var binding: ItemCoinInfoBinding
    var coinInfoList:List<CoinPriceInfo> = arrayListOf<CoinPriceInfo>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    var onCoinClickListener: OnCoinClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinInfoViewHolder {
//        val view =
//            LayoutInflater.from(parent.context).inflate(R.layout.item_coin_info, parent, false)
        binding = ItemCoinInfoBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return CoinInfoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CoinInfoViewHolder, position: Int) {
        val coin = coinInfoList.get(position)
        with(holder) {
            tvSymbs.text = String.format(context.resources.getString(R.string.symbs_template), coin.fromsymbol,coin.tosymbol)
            tvCourse.text = coin.price.toString()
            tvLastDate.text = String.format(context.resources.getString(R.string.last_update_template), coin.getFormattedTime())
            Picasso.get().load(coin.getFullImageUrl()).into(ivLogo)
            itemView.setOnClickListener { onCoinClickListener?.onCoinClick(coin) }
        }
    }

    override fun getItemCount(): Int {
        return coinInfoList.size
    }

    inner class CoinInfoViewHolder(binding: ItemCoinInfoBinding) : RecyclerView.ViewHolder(binding.root) {

        val ivLogo = binding.ivLogoCoin
        val tvSymbs = binding.tvSymbs
        val tvCourse = binding.tvCourse
        val tvLastDate = binding.tvLastDate
    }

    interface OnCoinClickListener{
        fun onCoinClick(coinPriceInfo: CoinPriceInfo)
    }
}