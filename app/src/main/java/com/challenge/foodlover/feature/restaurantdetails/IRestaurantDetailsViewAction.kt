package com.challenge.foodlover.feature.restaurantdetails

import com.challenge.foodlover.util.presentationarch.ViewAction

interface IRestaurantDetailsViewAction : ViewAction {
    fun toggleFavoriteStatus()
}
