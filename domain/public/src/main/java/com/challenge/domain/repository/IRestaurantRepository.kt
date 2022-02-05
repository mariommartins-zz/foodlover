package com.challenge.domain.repository

import com.challenge.domain.model.Restaurant

interface IRestaurantRepository {

    suspend fun getAll(): List<Restaurant>

    suspend fun addFavorite(restaurant: Restaurant)

    suspend fun removeFavorite(restaurant: Restaurant)

    fun observeFavoriteStatusBy(name: String): LiveData<Boolean>
}