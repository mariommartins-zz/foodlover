package com.challenge.foodlover.feature.restaurantlist

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val restaurantListModule = module {
    viewModel {
        RestaurantListViewModel(
            getSortedRestaurantList = get(),
            dispatcherMap = get()
        )
    }
}