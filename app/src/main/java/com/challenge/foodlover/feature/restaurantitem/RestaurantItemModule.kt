package com.challenge.foodlover.feature.restaurantitem

import com.challenge.domain.model.Restaurant
import org.koin.dsl.module

val restaurantItemModule = module {
    factory { (restaurant: Restaurant, onItemClick: (Restaurant) -> Unit) ->
        RestaurantItemViewModel(
            dispatcherMap = get(),
            restaurant = restaurant,
            onItemClick = onItemClick,
            observeRestaurantFavoriteStatus = get(),
            toggleRestaurantFavoriteStatus = get()
        )
    }
}
