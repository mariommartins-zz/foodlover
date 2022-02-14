package com.challenge.domain.usecase

import com.challenge.domain.model.Restaurant

interface ToggleRestaurantFavoriteStatusUseCase {
    suspend operator fun invoke(restaurant: Restaurant)
}