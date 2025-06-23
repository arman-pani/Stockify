package com.example.stockify.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.stockify.R
import com.example.stockify.adapters.DetailRowAdapter
import com.example.stockify.databinding.FragmentKeyStatisticsBinding
import com.example.stockify.databinding.InfoLayoutBinding
import com.example.stockify.models.DetailRowModel

class KeyStatisticsFragment: Fragment(R.layout.info_layout)  {

    private lateinit var binding: FragmentKeyStatisticsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentKeyStatisticsBinding.inflate(inflater, container, false)
        val recyclerViews = listOf(
            binding.valuationSection.sectionRecyclerView,
            binding.growthSection.sectionRecyclerView,
            binding.sharesSection.sectionRecyclerView,
            binding.shortInterestSection.sectionRecyclerView,
            binding.bookAndHistoricalSection.sectionRecyclerView
        )

        binding.valuationSection.sectionTitle.text = "Valuation"
        binding.growthSection.sectionTitle.text = "Growth"
        binding.sharesSection.sectionTitle.text = "Shares"
        binding.shortInterestSection.sectionTitle.text = "Short Interest"
        binding.bookAndHistoricalSection.sectionTitle.text = "Book and Historical Data"

        val dummyDetails = listOf(
            DetailRowModel("Market Cap", "1.5T"),
            DetailRowModel("P/E Ratio", "25.3"),
            DetailRowModel("EPS", "5.67"),
            DetailRowModel("Dividend Yield", "1.2%")
        )

        recyclerViews.forEach {
            it.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            it.adapter = DetailRowAdapter(dummyDetails)
        }

        return binding.root
    }
}