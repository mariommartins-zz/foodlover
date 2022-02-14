package com.challenge.foodlover.feature.restaurantlist

import android.animation.LayoutTransition
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import com.challenge.common.android.presentationarch.connectivity.ErrorHandlingFragment
import com.challenge.domain.model.Restaurant
import com.challenge.foodlover.FoodLoverGraphDirections
import com.challenge.foodlover.R
import com.challenge.foodlover.databinding.FragmentRestaurantListBinding
import com.challenge.foodlover.feature.restaurantitem.RestaurantAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class RestaurantListFragment : ErrorHandlingFragment() {

    override lateinit var binding: FragmentRestaurantListBinding
    override val viewModel: RestaurantListViewModel by viewModel()

    private lateinit var restaurantAdapter: RestaurantAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentRestaurantListBinding.inflate(inflater).apply { binding = this }.root

    override fun doOnViewCreated(view: View, savedInstanceState: Bundle?) {
        setupView()
        observeEvents()
    }

    private fun setupView() = with(binding) {
        restaurantListContainerSrl.apply {
            isRefreshing = true
            setOnRefreshListener { this@RestaurantListFragment.viewModel.onSwipeToRefresh() }
            setColorSchemeResources(R.color.yellow, R.color.green)
            layoutTransition?.enableTransitionType(LayoutTransition.CHANGING)
        }
        restaurantAdapter = RestaurantAdapter(viewLifecycleOwner, ::navigateToRestaurantDetails)
        restaurantListContainerRv.adapter = restaurantAdapter
        setupFilterOptions()
    }

    private fun observeEvents() = with(viewModel.state) {
        restaurants.observe(viewLifecycleOwner) {
            restaurantAdapter.submitList(it)
            binding.restaurantListContainerSrl.isRefreshing = false
            binding.restaurantListContainerRv.scrollToPosition(0)
        }
    }

    private fun setupFilterOptions() = with(binding.restaurantListSortingOptionsSp) {
        val filterOptions = resources.getStringArray(R.array.restaurant_sorting_options)
        adapter = ArrayAdapter(context, android.R.layout.simple_selectable_list_item, filterOptions)
        onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) = viewModel.onFilterOptionSelected(position)

            override fun onNothingSelected(parent: AdapterView<*>?) = Unit
        }
    }

    private fun navigateToRestaurantDetails(restaurant: Restaurant) {
        findNavController()
            .navigate(FoodLoverGraphDirections.navigateToRestaurantDetails(restaurant))
    }
}