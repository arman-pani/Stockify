package com.example.stockify.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.stockify.R
import com.example.stockify.adapters.ProfileTagAdapter
import com.example.stockify.databinding.ActivityCompanyProfileBinding
import com.example.stockify.fragments.FinancialsFragment
import com.example.stockify.fragments.KeyStatisticsFragment
import com.example.stockify.room.WatchlistItemModel
import com.example.stockify.utils.ProfileTag
import com.example.stockify.utils.loadImage
import com.example.stockify.viewModels.StockProfileViewModel
import com.example.stockify.viewModels.StockProfileViewModelFactory
import com.example.stockify.viewModels.WatchlistViewModel

class StockProfileActivity: AppCompatActivity() {

    private lateinit var binding: ActivityCompanyProfileBinding

    private lateinit var viewModel: StockProfileViewModel

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.stock_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.addToWatchlist -> {
                viewModel.onAddToWatchlist(
                    WatchlistItemModel(
                        0,
                        viewModel.ticker,
                        viewModel.companyName,
                        viewModel.logoUrl,
                        viewModel.stockValue,
                        viewModel.percentChange
                    )
                )
            }

            R.id.share -> {}
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tags = listOf(
            ProfileTag.KEY_STATISTICS,
            ProfileTag.FINANCIALS,
            ProfileTag.NEWS,
        )

        binding = ActivityCompanyProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = StockProfileViewModelFactory(applicationContext)
        viewModel = ViewModelProvider(this, factory)[StockProfileViewModel::class.java]
        viewModel.loadStockDetails()

        binding.companyNameTextView.text = viewModel.companyName
        binding.companySymbolTextView.text = viewModel.ticker
        binding.companyLogoImageView.loadImage(viewModel.logoUrl)
        binding.valueTextView.text = String.format("$%.2f", viewModel.stockValue)
        binding.percentageTextView.text = String.format("%.2f%%", viewModel.percentChange)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        binding.toolbar.setNavigationOnClickListener {finish()}

        binding.tagsRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        binding.tagsRecyclerView.adapter = ProfileTagAdapter(tags) { tag ->
            val fragment = when (tag) {
                ProfileTag.KEY_STATISTICS -> KeyStatisticsFragment()
                ProfileTag.FINANCIALS -> FinancialsFragment()
                ProfileTag.NEWS -> FinancialsFragment()
            }
            supportFragmentManager.beginTransaction()
                .replace(R.id.company_profile_nav_host, fragment)
                .commit()
        }


    }
}