package com.challenge.foodlover.feature.restaurantlist

import androidx.lifecycle.viewModelScope
import com.challenge.domain.dispatcher.DispatcherMap
import com.challenge.domain.model.RestaurantFilterOption
import com.challenge.domain.usecase.GetSortedRestaurantListUseCase
import com.challenge.foodlover.util.presentationarch.ViewModel
import kotlinx.coroutines.launch

class RestaurantListViewModel(
    private val getSortedRestaurantList: GetSortedRestaurantListUseCase,
    private val dispatcherMap: DispatcherMap,
    private val mutableState: RestaurantListViewState = RestaurantListViewState()
) : ViewModel<IRestaurantListViewState, IRestaurantListViewAction>(), IRestaurantListViewAction {

    override val state: IRestaurantListViewState get() = mutableState
    override val action: IRestaurantListViewAction get() = this

    private var currentFilterOption: RestaurantFilterOption = RestaurantFilterOption.BEST_MATCH

    init {
        updateRestaurantList()
    }

    override fun onSwipeToRefresh() = updateRestaurantList()

    override fun onFilterOptionSelected(filterValue: Int) {
        RestaurantFilterOption.getByValue(filterValue)?.let {
            if (currentFilterOption != it) {
                currentFilterOption = it
                updateRestaurantList()
            }
        }
    }

    private fun updateRestaurantList() {
        viewModelScope.launch(dispatcherMap.io) {
            val result = getSortedRestaurantList(currentFilterOption)
            mutableState.postRestaurants(result)
        }
    }
}