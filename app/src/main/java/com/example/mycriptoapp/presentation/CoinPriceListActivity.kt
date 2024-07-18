package com.example.mycriptoapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mycriptoapp.R
import com.example.mycriptoapp.presentation.adapters.CoinInfoAdapter
import com.example.mycriptoapp.databinding.ActivityCoinPriceListBinding
import com.example.mycriptoapp.domain.CoinInfo


class CoinPriceListActivity : AppCompatActivity() {
    lateinit var binding:ActivityCoinPriceListBinding
    private lateinit var viewModel: CoinViewModel
    private lateinit var adapter:CoinInfoAdapter
    private var coinInfoContainer: FragmentContainerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityCoinPriceListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()
        coinInfoContainer = binding.coinInfoContainerLand

        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        viewModel.coinInfoList.observe(this, Observer {
            adapter.submitList(it)
        })

    }

    private fun setupRecyclerView() {
        adapter = CoinInfoAdapter(this)
        adapter.onCoinClickListener = object : CoinInfoAdapter.OnCoinClickListener{
            override fun onCoinClick(coinPriceInfo: CoinInfo) {
                if (!isOneColumnMode()){
                    launchFragment(CoinDetailFragment.newInstance(coinPriceInfo.fromsymbol))
                }
                else {
                    launchDetailActivity(coinPriceInfo.fromsymbol)
                }
            }
        }
        binding.rvCoinPriceList.adapter = adapter
    }

    private fun launchFragment(fragment: Fragment){
        supportFragmentManager.popBackStack()//удаляю старый фрагмент
        supportFragmentManager.beginTransaction()
            .replace(R.id.coinInfoContainerLand, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun launchDetailActivity(fSym: String){
        val intent = CoinDetailActivity.newIntent(
            this@CoinPriceListActivity,
            fSym
        )
        startActivity(intent)
    }

    private fun isOneColumnMode(): Boolean{
        return coinInfoContainer == null
    }


}