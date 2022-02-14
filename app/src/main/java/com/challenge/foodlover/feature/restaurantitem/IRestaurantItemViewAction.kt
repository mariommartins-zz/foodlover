package com.challenge.foodlover.feature.restaurantitem

import com.challenge.common.android.presentationarch.ViewAction

interface IRestaurantItemViewAction : ViewAction {
    fun toggleFavoriteStatus()
    fun onCardClicked()
}