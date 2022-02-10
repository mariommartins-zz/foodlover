package com.challenge.foodlover.feature.restaurantdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.challenge.foodlover.databinding.DialogRestaurantDetailsBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class RestaurantDetailsDialogFragment : BottomSheetDialogFragment() {

    private val args: RestaurantDetailsDialogFragmentArgs by navArgs()
    private lateinit var binding: DialogRestaurantDetailsBinding
    private val viewModel: RestaurantDetailsViewModel by viewModel { parametersOf(args.restaurant) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = DialogRestaurantDetailsBinding
        .inflate(inflater)
        .apply {
            lifecycleOwner = viewLifecycleOwner
            state = viewModel.state
            action = viewModel

            binding = this
        }.root
}