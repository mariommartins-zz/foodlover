package com.challenge.foodlover.feature.restaurantitem

import androidx.lifecycle.LiveData
import com.challenge.domain.model.Restaurant
import com.challenge.domain.model.RestaurantOpenStatus
import com.challenge.domain.usecase.ObserveRestaurantFavoriteStatusUseCase
import com.challenge.foodlover.util.presentationarch.ViewState

interface IRestaurantItemViewState : ViewState {
    val name: String
    val rating: Float
    val status: RestaurantOpenStatus

    val isFavorite: LiveData<Boolean>
}

class RestaurantItemViewState(
    private val restaurant: Restaurant,
    observeRestaurantFavoriteStatus: ObserveRestaurantFavoriteStatusUseCase,
) : IRestaurantItemViewState {
    override val name get() = restaurant.name
    override val rating get() = restaurant.ratingAverage.toFloat()
    override val status get() = restaurant.status

    override val isFavorite: LiveData<Boolean> = observeRestaurantFavoriteStatus(restaurant)
}