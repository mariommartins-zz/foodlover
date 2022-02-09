package com.challenge.foodlover.feature.restaurantitem

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.challenge.domain.dispatcher.DispatcherMap
import com.challenge.domain.model.Restaurant
import com.challenge.domain.usecase.ObserveRestaurantFavoriteStatusUseCase
import com.challenge.domain.usecase.ToggleRestaurantFavoriteStatusUseCase
import kotlinx.coroutines.launch

class RestaurantItemViewModel(
    private val dispatcherMap: DispatcherMap,
    private val restaurant: Restaurant,
    private val onItemClick: (Restaurant) -> Unit,
    observeRestaurantFavoriteStatus: ObserveRestaurantFavoriteStatusUseCase,
    private val toggleRestaurantFavoriteStatus: ToggleRestaurantFavoriteStatusUseCase
) : ViewModel() {

    val name get() = restaurant.name
    val rating get() = restaurant.ratingAverage.toFloat()
    val status get() = restaurant.status

    val isFavorite: LiveData<Boolean> = observeRestaurantFavoriteStatus(restaurant)

    fun toggleFavoriteStatus() {
        viewModelScope.launch(dispatcherMap.io) { toggleRestaurantFavoriteStatus(restaurant) }
    }

    fun onCardClicked() = onItemClick(restaurant)
}
