package com.challenge.foodlover.feature.restaurantitem

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.challenge.domain.dispatcher.DispatcherMap
import com.challenge.domain.model.Restaurant
import com.challenge.domain.repository.IRestaurantRepository
import kotlinx.coroutines.launch

class RestaurantItemViewModel(
    private val dispatcherMap: DispatcherMap,
    private val restaurant: Restaurant,
    private val repository: IRestaurantRepository
) : ViewModel() {

    val name get() = restaurant.name
    val rating get() = restaurant.ratingAverage.toFloat()
    val status get() = restaurant.status

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
