package com.challenge.foodlover.feature.restaurantitem

import com.challenge.foodlover.util.presentationarch.ViewAction

interface IRestaurantItemViewAction : ViewAction {
    fun toggleFavoriteStatus()
    fun onCardClicked()
}