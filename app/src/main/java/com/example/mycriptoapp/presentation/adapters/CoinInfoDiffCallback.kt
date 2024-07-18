package com.example.mycriptoapp.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.mycriptoapp.domain.CoinInfo

class CoinInfoDiffCallback:DiffUtil.ItemCallback<CoinInfo>() {

    override fun areItemsTheSame(oldItem: CoinInfo, newItem: CoinInfo): Boolean {
        return oldItem.fromsymbol==newItem.fromsymbol
    }

    override fun areContentsTheSame(oldItem: CoinInfo, newItem: CoinInfo): Boolean {
        return oldItem.equals(newItem)
    }
}