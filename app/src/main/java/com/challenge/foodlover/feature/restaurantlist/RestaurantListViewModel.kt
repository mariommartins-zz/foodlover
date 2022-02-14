package com.challenge.foodlover.feature.restaurantlist

import com.challenge.common.android.presentationarch.connectivity.ErrorHandlingViewModel
import com.challenge.domain.model.RestaurantFilterOption
import com.challenge.domain.usecase.GetSortedRestaurantListUseCase
import com.challenge.kotlin.DispatcherMap

class RestaurantListViewModel(
    private val getSortedRestaurantList: GetSortedRestaurantListUseCase,
    override val dispatcherMap: DispatcherMap,
    private val mutableState: RestaurantListViewState = RestaurantListViewState()
) : ErrorHandlingViewModel<IRestaurantListViewState, IRestaurantListViewAction>(),
    IRestaurantListViewAction {

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
        performRequestSafely {
            val result = getSortedRestaurantList(currentFilterOption)
            mutableState.postRestaurants(result)
        }
    }
}