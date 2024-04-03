package com.example.mycriptoapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mycriptoapp.databinding.FragmentCoinDetailBinding
import com.squareup.picasso.Picasso

class CoinDetailFragment() : Fragment() {

    private lateinit var binding: FragmentCoinDetailBinding
    private lateinit var viewModel: CoinViewModel
    private var fSymbol = FSYM_UNKNOWN

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseParams()
    }

    private fun parseParams() {
        val args = requireArguments()
        if (!args.containsKey(EXTRA_FROM_SYMBOL)) {
            throw RuntimeException("Param from symbol is absent")
        }
         fSymbol = args.getString(EXTRA_FROM_SYMBOL).toString()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentCoinDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        launch()
    }

    private fun launch() {
        viewModel.getDetailInfo(fSymbol).observe(viewLifecycleOwner){
            with(binding) {
                tvPrice.text = it.price.toString()
                tvMinPrice.text = it.lowday.toString()
                tvMaxPrice.text = it.highday.toString()
                tvLastMarket.text = it.lastmarket
                tvLastUpdate.text = it.lastupdate
                tvFromSymbol.text = it.fromsymbol
                tvToSymbol.text = it.tosymbol

                Picasso.get().load(it.imageurl).into(ivLogoCoin)
            }
        }
    }

    companion object {
        private const val EXTRA_FROM_SYMBOL = "fSym"
        private const val FSYM_UNKNOWN = ""
        fun newInstance(fSym: String): CoinDetailFragment {
            return CoinDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(EXTRA_FROM_SYMBOL, fSym)
                }
            }
        }
    }
}