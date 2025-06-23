package com.example.stockify.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.stockify.R
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.stockify.adapters.DetailRowAdapter
import com.example.stockify.databinding.FragmentFinancialsBinding
import com.example.stockify.models.DetailRowModel

class FinancialsFragment: Fragment(R.layout.fragment_financials) {
    private lateinit var binding: FragmentFinancialsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentFinancialsBinding.inflate(inflater, container, false)

        val recyclerViews = listOf(
            binding.stockOverview.sectionRecyclerView,
            binding.growthAndMargins.sectionRecyclerView,
            binding.financialHealth.sectionRecyclerView,
            binding.revenueAndMargins.sectionRecyclerView
        )

        binding.stockOverview.sectionTitle.text = "Stock Overview"
        binding.growthAndMargins.sectionTitle.text = "Growth and Margins"
        binding.financialHealth.sectionTitle.text = "Financial Health"
        binding.revenueAndMargins.sectionTitle.text = "Revenue and Margins"

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