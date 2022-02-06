package com.challenge.domain.usecase

import com.challenge.domain.model.Restaurant

interface GetSortedRestaurantListUseCase {
    suspend operator fun invoke(): List<Restaurant>
}