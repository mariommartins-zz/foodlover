package com.challenge.data.remote.datasource

import com.challenge.data.remote.mapper.MapRestaurantFromResponseToModelAlias
import com.challenge.data.remote.network.FoodLoverApi
import com.challenge.domain.model.Restaurant

internal class RestaurantRemoteDataSource(
    private val api: FoodLoverApi,
    private val mapFromResponseToModel: MapRestaurantFromResponseToModelAlias
) : IRestaurantRemoteDataSource {
    override suspend fun fetchRestaurants(): List<Restaurant> =
        api.fetchRestaurants().map { mapFromResponseToModel(it) }
}
