package com.example.mycriptoapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mycriptoapp.adapters.CoinInfoAdapter
import com.example.mycriptoapp.databinding.ActivityCoinPriceListBinding
import com.example.mycriptoapp.pojo.CoinPriceInfo


class CoinPriceListActivity : AppCompatActivity() {
    lateinit var binding:ActivityCoinPriceListBinding
    private lateinit var viewModel: CoinViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoinPriceListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        val adapter = CoinInfoAdapter(this)
        adapter.onCoinClickListener = object : CoinInfoAdapter.OnCoinClickListener{
            override fun onCoinClick(coinPriceInfo: CoinPriceInfo) {
                val intent = CoinDetailActivity.newIntent(this@CoinPriceListActivity, coinPriceInfo.fromsymbol)
                startActivity(intent)
            }
        }

        binding.rvCoinPriceList.adapter = adapter
        viewModel.pricelist.observe(this, Observer {

            adapter.coinInfoList = it

        })


    }


}