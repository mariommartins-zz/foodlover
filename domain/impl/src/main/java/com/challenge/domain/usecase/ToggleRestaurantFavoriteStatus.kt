package com.challenge.domain.usecase

import com.challenge.domain.model.Restaurant
import com.challenge.domain.repository.IRestaurantRepository

internal class ToggleRestaurantFavoriteStatus(private val repository: IRestaurantRepository) :
    ToggleRestaurantFavoriteStatusUseCase {
    override suspend fun invoke(restaurant: Restaurant) {
        if (restaurant.isFavorite)
            repository.removeFavorite(restaurant)
        else
            repository.addFavorite(restaurant)
    }
}
