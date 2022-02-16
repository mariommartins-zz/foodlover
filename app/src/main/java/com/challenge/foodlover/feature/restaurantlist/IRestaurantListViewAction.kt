package com.challenge.foodlover.feature.restaurantlist

import com.challenge.common.android.presentationarch.ViewAction

interface IRestaurantListViewAction : ViewAction {
    fun onSwipeToRefresh()
    fun onFilterOptionSelected(filterPosition: Int)
}