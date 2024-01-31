package com.example.mycriptoapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mycriptoapp.databinding.ActivityCoinDetailBinding
import com.squareup.picasso.Picasso

class CoinDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityCoinDetailBinding
    private lateinit var viewModel: CoinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityCoinDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (!intent.hasExtra(EXTRA_FROM_SYMBOL)){
            finish()
            return
        }
        val fromSymbol = intent.getStringExtra(EXTRA_FROM_SYMBOL)
        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        if (fromSymbol != null) {
            viewModel.getDetailInfo(fromSymbol).observe(this, Observer {
                with(binding) {
                    tvPrice.text = it.price.toString()
                    tvMinPrice.text = it.lowday.toString()
                    tvMaxPrice.text = it.highday.toString()
                    tvLastMarket.text = it.lastmarket
                    tvLastUpdate.text = it.getFormattedTime()

                    tvFromSymbol.text = it.fromsymbol
                    tvToSymbol.text = it.tosymbol

                    Picasso.get().load(it.getFullImageUrl()).into(ivLogoCoin)
                }
            })
        }
    }

    companion object{

        private const val EXTRA_FROM_SYMBOL = "fSym"
        fun newIntent(context: Context, fromSymbol: String): Intent {
            val intent = Intent(context, CoinDetailActivity::class.java)
            intent.putExtra(EXTRA_FROM_SYMBOL, fromSymbol)
            return intent
        }

    }



}