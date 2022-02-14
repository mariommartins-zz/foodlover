package com.challenge.foodlover.feature.restaurantitem

import com.challenge.domain.model.Restaurant
import org.koin.dsl.module

val restaurantItemModule = module {
    factory { (restaurant: Restaurant, onItemClicked: (Restaurant) -> Unit) ->
        RestaurantItemViewModel(
            dispatcherMap = get(),
            restaurant = restaurant,
            onItemClicked = onItemClicked,
            observeRestaurantFavoriteStatus = get(),
            toggleRestaurantFavoriteStatus = get()
        )
    }
}
