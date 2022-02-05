package com.challenge.data.remote.datasource

import com.challenge.data.remote.mapper.RestaurantResponseToModelMapperAlias
import com.challenge.data.remote.network.FoodLoverApi
import com.challenge.domain.model.Restaurant

internal class RestaurantRemoteDataSource(
    private val api: FoodLoverApi,
    private val mapper: RestaurantResponseToModelMapperAlias
) : IRestaurantRemoteDataSource {
    override suspend fun fetchRestaurants(): List<Restaurant> =
        api.fetchRestaurants().map { mapper.from(it) }
}
