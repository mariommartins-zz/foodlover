package com.challenge.data.remote.datasource

import com.challenge.domain.model.Restaurant

internal interface IRestaurantRemoteDataSource {
    suspend fun fetchRestaurants(): List<Restaurant>?
}
