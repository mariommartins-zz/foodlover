package com.challenge.foodlover

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    viewModel {
        FoodLoverViewModel(
            getSortedRestaurantList = get(),
            toggleRestaurantFavoriteStatus = get(),
            dispatcherMap = get()
        )
    }
}