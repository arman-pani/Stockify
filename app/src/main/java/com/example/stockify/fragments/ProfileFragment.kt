package com.example.stockify.fragments

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.stockify.R
import com.example.stockify.adapters.ProfileRowAdapter
import com.example.stockify.databinding.FragmentProfileBinding
import com.example.stockify.models.ProfileRowModel

class ProfileFragment: Fragment(R.layout.fragment_profile) {
    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: android.view.LayoutInflater,
        container: android.view.ViewGroup?,
        savedInstanceState: android.os.Bundle?
    ): android.view.View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)

        val rows = listOf(
            ProfileRowModel("Account", R.drawable.add),
            ProfileRowModel("Watchlist", R.drawable.watchlist),
            ProfileRowModel("Settings", R.drawable.add)
        )

        binding.profileRecyclerView.adapter = ProfileRowAdapter(rows)
        binding.profileRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        return binding.root

    }
}