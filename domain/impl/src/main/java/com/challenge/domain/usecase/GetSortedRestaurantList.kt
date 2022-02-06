package com.challenge.domain.usecase

import com.challenge.domain.model.Restaurant
import com.challenge.domain.repository.IRestaurantRepository

internal class GetSortedRestaurantList(private val repository: IRestaurantRepository) :
    GetSortedRestaurantListUseCase {
    override suspend operator fun invoke(): List<Restaurant> {
        return repository.getAll()
    }
}
