package com.challenge.foodlover.feature.restaurantdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.challenge.domain.dispatcher.DispatcherMap
import com.challenge.domain.model.Restaurant
import com.challenge.domain.repository.IRestaurantRepository
import kotlinx.coroutines.launch

class RestaurantDetailsViewModel(
    private val dispatcherMap: DispatcherMap,
    private val restaurant: Restaurant,
    private val repository: IRestaurantRepository
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

    private val _isFavorite = MediatorLiveData<Boolean>().apply {
        value = restaurant.isFavorite
        addSource(repository.observeFavoriteStatusBy(restaurant.name)) {
            value = it
            restaurant.isFavorite = it
        }
    }
    val isFavorite: LiveData<Boolean> get() = _isFavorite

    fun toggleFavoriteStatus() {
        viewModelScope.launch(dispatcherMap.io) {
            if (restaurant.isFavorite) repository.removeFavorite(restaurant)
            else repository.addFavorite(restaurant)
        }
    }
}
