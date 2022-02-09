package com.challenge.foodlover.feature.restaurantdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.challenge.domain.dispatcher.DispatcherMap
import com.challenge.domain.model.Restaurant
import com.challenge.domain.usecase.ObserveRestaurantFavoriteStatusUseCase
import com.challenge.domain.usecase.ToggleRestaurantFavoriteStatusUseCase
import kotlinx.coroutines.launch

class RestaurantDetailsViewModel(
    private val dispatcherMap: DispatcherMap,
    private val restaurant: Restaurant,
    observeRestaurantFavoriteStatus: ObserveRestaurantFavoriteStatusUseCase,
    private val toggleRestaurantFavoriteStatus: ToggleRestaurantFavoriteStatusUseCase
) : ViewModel() {

    val name get() = restaurant.name
    val status get() = restaurant.status
    val bestMatch get() = restaurant.bestMatch.toString()
    val newest get() = restaurant.newest.toString()
    val ratingAverage get() = restaurant.ratingAverage.toString()
    val distance get() = restaurant.distance.toString()
    val popularity get() = restaurant.popularity.toString()
    val averageProductPrice get() = restaurant.averageProductPrice.toString()
    val deliveryCosts get() = restaurant.deliveryCosts.toString()
    val minCost get() = restaurant.minCost.toString()

    val isFavorite: LiveData<Boolean> = observeRestaurantFavoriteStatus(restaurant)

    fun toggleFavoriteStatus() {
        viewModelScope.launch(dispatcherMap.io) { toggleRestaurantFavoriteStatus(restaurant) }
    }
}
