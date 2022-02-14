package com.challenge.foodlover.feature.restaurantdetails

import com.challenge.common.android.presentationarch.ViewAction

interface IRestaurantDetailsViewAction : ViewAction {
    fun toggleFavoriteStatus()
}
