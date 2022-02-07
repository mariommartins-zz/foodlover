package com.challenge.foodlover

import com.challenge.foodlover.feature.main.FoodLoverViewModel
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