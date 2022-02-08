package com.challenge.domain.usecase

import com.challenge.domain.model.Restaurant
import com.challenge.domain.model.RestaurantFilterOption

interface GetSortedRestaurantListUseCase {
    suspend operator fun invoke(filterOption: RestaurantFilterOption): List<Restaurant>
}