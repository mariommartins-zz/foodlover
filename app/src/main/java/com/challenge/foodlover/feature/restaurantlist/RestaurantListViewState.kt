package com.challenge.foodlover.feature.restaurantlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.challenge.domain.model.Restaurant
import com.challenge.foodlover.util.presentationarch.ViewState

interface IRestaurantListViewState : ViewState {
    val restaurants: LiveData<List<Restaurant>>
}

class RestaurantListViewState : IRestaurantListViewState {
    private val _restaurants = MutableLiveData<List<Restaurant>>()
    override val restaurants: LiveData<List<Restaurant>> get() = _restaurants

    fun postRestaurants(items: List<Restaurant>) = _restaurants.postValue(items)
}