package com.challenge.foodlover.feature.test

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val testModule = module {
    viewModel {
        TestViewModel(
            getSortedRestaurantList = get(),
            toggleRestaurantFavoriteStatus = get(),
            dispatcherMap = get()
        )
    }
}