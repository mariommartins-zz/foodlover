package com.challenge.foodlover.feature.restaurantlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.challenge.foodlover.databinding.FragmentRestaurantListBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class RestaurantListFragment : Fragment() {

    private lateinit var binding: FragmentRestaurantListBinding
    private val viewModel: RestaurantListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentRestaurantListBinding.inflate(inflater).apply {
        lifecycleOwner = viewLifecycleOwner
        binding = this
    }.root
}