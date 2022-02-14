package com.challenge.foodlover.feature.restaurantdetails

import com.challenge.domain.model.Restaurant
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val restaurantDetailsModule = module {
    viewModel { (restaurant: Restaurant) ->
        RestaurantDetailsViewModel(
            dispatcherMap = get(),
            restaurant = restaurant,
            observeRestaurantFavoriteStatus = get(),
            toggleRestaurantFavoriteStatus = get()
        )
    }
}