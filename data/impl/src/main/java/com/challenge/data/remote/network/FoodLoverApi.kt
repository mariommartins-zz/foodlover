package com.challenge.data.remote.network

import com.challenge.data.remote.response.RestaurantResponse

internal interface FoodLoverApi {

    suspend fun fetchRestaurants(): List<RestaurantResponse>
}
