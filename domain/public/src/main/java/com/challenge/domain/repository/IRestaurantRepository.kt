package com.challenge.domain.repository

import androidx.lifecycle.LiveData
import com.challenge.domain.model.Restaurant

interface IRestaurantRepository {

    suspend fun getAll(): List<Restaurant>

    suspend fun addFavorite(restaurant: Restaurant)

    suspend fun removeFavorite(restaurant: Restaurant)

    suspend fun isFavorite(restaurant: Restaurant): Boolean

    fun observeFavoriteStatusBy(name: String): LiveData<Boolean>
}