package com.challenge.foodlover.feature.restaurantitem

import com.challenge.domain.model.Restaurant
import org.koin.dsl.module

val restaurantItemModule = module {
    factory { (restaurant: Restaurant) ->
        RestaurantItemViewModel(
            dispatcherMap = get(),
            restaurant = restaurant,
            repository = get()
        )
    }
}
