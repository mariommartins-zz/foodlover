package com.challenge.foodlover.feature.restaurantlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.challenge.domain.dispatcher.DispatcherMap
import com.challenge.domain.model.Restaurant
import com.challenge.domain.model.RestaurantFilterOption
import com.challenge.domain.usecase.GetSortedRestaurantListUseCase
import kotlinx.coroutines.launch

class RestaurantListViewModel(
    private val getSortedRestaurantList: GetSortedRestaurantListUseCase,
    private val dispatcherMap: DispatcherMap
) : ViewModel() {

    private val _restaurants = MutableLiveData<List<Restaurant>>()
    val restaurants: LiveData<List<Restaurant>> = _restaurants

    private var currentFilterOption: RestaurantFilterOption = RestaurantFilterOption.BEST_MATCH

    init {
        updateRestaurantList()
    }

    fun onSwipeToRefresh() = updateRestaurantList()

    private fun updateRestaurantList() {
        viewModelScope.launch(dispatcherMap.io) {
            val result = getSortedRestaurantList(currentFilterOption)
            _restaurants.postValue(result)
        }
    }

    fun onFilterOptionSelected(filterValue: Int) {
        RestaurantFilterOption.getByValue(filterValue)?.let {
            if(currentFilterOption != it) {
                currentFilterOption = it
                updateRestaurantList()
            }
        }
    }
}