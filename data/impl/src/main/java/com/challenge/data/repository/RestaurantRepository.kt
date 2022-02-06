package com.challenge.data.repository

import com.challenge.data.local.datasource.IRestaurantLocalDataSource
import com.challenge.data.remote.datasource.IRestaurantRemoteDataSource
import com.challenge.domain.model.Restaurant
import com.challenge.domain.repository.IRestaurantRepository

internal class RestaurantRepository(
    private val localDataSource: IRestaurantLocalDataSource,
    private val remoteDataSource: IRestaurantRemoteDataSource
) : IRestaurantRepository {

    override suspend fun getAll() =
        remoteDataSource
            .fetchRestaurants()
            .orEmpty()
            .onEach { it.isFavorite = isFavorite(it) }

    override suspend fun addFavorite(restaurant: Restaurant) = localDataSource.add(restaurant)

    override suspend fun removeFavorite(restaurant: Restaurant) = localDataSource.remove(restaurant)

    override suspend fun isFavorite(restaurant: Restaurant) =
        localDataSource.isFavorite(restaurant.name)

    override fun observeFavoriteStatusBy(name: String) =
        localDataSource.observeFavoriteStatusBy(name)
}
