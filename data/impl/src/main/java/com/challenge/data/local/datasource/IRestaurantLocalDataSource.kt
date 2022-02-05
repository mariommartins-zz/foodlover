package com.challenge.data.local.datasource

import androidx.lifecycle.LiveData
import com.challenge.domain.model.Restaurant

internal interface IRestaurantLocalDataSource {
    suspend fun add(restaurant: Restaurant)

    suspend fun remove(restaurant: Restaurant)

    suspend fun isFavorite(name: String): Boolean

    fun observeFavoriteStatusBy(name: String): LiveData<Boolean>
}
