package com.challenge.foodlover.feature.restaurantlist

import com.challenge.foodlover.util.presentationarch.ViewAction

interface IRestaurantListViewAction : ViewAction {
    fun onSwipeToRefresh()
    fun onFilterOptionSelected(filterValue: Int)
}