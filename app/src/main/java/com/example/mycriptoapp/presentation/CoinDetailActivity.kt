package com.example.mycriptoapp.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mycriptoapp.R
import com.example.mycriptoapp.databinding.ActivityCoinDetailBinding
import com.squareup.picasso.Picasso

class CoinDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityCoinDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityCoinDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (!intent.hasExtra(EXTRA_FROM_SYMBOL)) {
            finish()
            return
        }
        val fromSymbol = intent.getStringExtra(EXTRA_FROM_SYMBOL) ?: ""

        supportFragmentManager.beginTransaction()
            .replace(R.id.coinDetailContainer, CoinDetailFragment.newInstance(fromSymbol))
            .commit()

    }

    companion object {

        private const val EXTRA_FROM_SYMBOL = "fSym"
        fun newIntent(context: Context, fromSymbol: String): Intent {
            val intent = Intent(context, CoinDetailActivity::class.java)
            intent.putExtra(EXTRA_FROM_SYMBOL, fromSymbol)
            return intent
        }

    }


}