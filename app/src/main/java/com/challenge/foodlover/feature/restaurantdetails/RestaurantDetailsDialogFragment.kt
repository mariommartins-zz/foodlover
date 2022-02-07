package com.challenge.foodlover.feature.restaurantdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.challenge.foodlover.databinding.DialogRestaurantDetailsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class RestaurantDetailsDialogFragment : DialogFragment() {

    //    private val arguments: ShowQrCodeDialogFragmentArgs by navArgs()
    private lateinit var binding: DialogRestaurantDetailsBinding
    private val viewModel: RestaurantDetailsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = DialogRestaurantDetailsBinding
        .inflate(inflater)
        .apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = this@RestaurantDetailsDialogFragment.viewModel

            binding = this
        }.root
}