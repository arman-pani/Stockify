package com.example.stockify.fragments

import androidx.fragment.app.Fragment
import com.example.stockify.R

class ProfileFragment: Fragment(R.layout.fragment_profile) {
    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        // Initialize any data or state here if needed
    }

    override fun onCreateView(
        inflater: android.view.LayoutInflater,
        container: android.view.ViewGroup?,
        savedInstanceState: android.os.Bundle?
    ): android.view.View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }
}