package com.challenge.foodlover.feature.test

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.challenge.common.android.SingleLiveEvent
import com.challenge.domain.dispatcher.DispatcherMap
import com.challenge.domain.model.Restaurant
import com.challenge.domain.model.RestaurantFilterOption
import com.challenge.domain.usecase.GetSortedRestaurantListUseCase
import com.challenge.domain.usecase.ToggleRestaurantFavoriteStatusUseCase
import kotlinx.coroutines.launch

class TestViewModel(
    private val getSortedRestaurantList: GetSortedRestaurantListUseCase,
    private val toggleRestaurantFavoriteStatus: ToggleRestaurantFavoriteStatusUseCase,
    private val dispatcherMap: DispatcherMap
) : ViewModel() {

    private val _restaurants = MutableLiveData<List<Restaurant>>()
    val restaurants: LiveData<List<Restaurant>> = _restaurants

    private val _onMainClicked = SingleLiveEvent<Any>()
    val onMainClicked get() = _onMainClicked

    init {
        updateRestaurantList()
    }

    fun onTestButtonClicked() = updateRestaurantList()

    fun onMainButtonClicked() = _onMainClicked.call()

    private fun updateRestaurantList() {
        viewModelScope.launch(dispatcherMap.io) {
            val result = getSortedRestaurantList(RestaurantFilterOption.BEST_MATCH)
            _restaurants.postValue(result)
        }
    }

    fun toggleFavoriteRestaurant(position: Int) {
        viewModelScope.launch(dispatcherMap.io) {
            val items = _restaurants.value.orEmpty()
            toggleRestaurantFavoriteStatus(items[position])
            _restaurants.postValue(items)
        }
    }
}
