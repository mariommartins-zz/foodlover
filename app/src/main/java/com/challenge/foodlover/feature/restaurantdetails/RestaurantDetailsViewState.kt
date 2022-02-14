package com.challenge.foodlover.feature.restaurantdetails

import androidx.lifecycle.LiveData
import com.challenge.common.android.presentationarch.ViewState
import com.challenge.domain.model.Restaurant
import com.challenge.domain.model.RestaurantOpenStatus
import com.challenge.domain.usecase.ObserveRestaurantFavoriteStatusUseCase

interface IRestaurantDetailsViewState : ViewState {
    val name: String
    val status: RestaurantOpenStatus
    val bestMatch: String
    val newest: String
    val ratingAverage: String
    val distance: String
    val popularity: String
    val averageProductPrice: String
    val deliveryCosts: String
    val minCost: String

    val isFavorite: LiveData<Boolean>
}

class RestaurantDetailsViewState(
    private val restaurant: Restaurant,
    observeRestaurantFavoriteStatus: ObserveRestaurantFavoriteStatusUseCase
) : IRestaurantDetailsViewState {

    override val name get() = restaurant.name
    override val status get() = restaurant.status
    override val bestMatch get() = restaurant.bestMatch.toString()
    override val newest get() = restaurant.newest.toString()
    override val ratingAverage get() = restaurant.ratingAverage.toString()
    override val distance get() = restaurant.distance.toString()
    override val popularity get() = restaurant.popularity.toString()
    override val averageProductPrice get() = restaurant.averageProductPrice.toString()
    override val deliveryCosts get() = restaurant.deliveryCosts.toString()
    override val minCost get() = restaurant.minCost.toString()

    override val isFavorite: LiveData<Boolean> = observeRestaurantFavoriteStatus(restaurant)
}
