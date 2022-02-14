package com.challenge.domain.usecase

import androidx.lifecycle.MediatorLiveData
import com.challenge.domain.model.Restaurant
import com.challenge.domain.repository.IRestaurantRepository

internal class ObserveRestaurantFavoriteStatus(
    private val repository: IRestaurantRepository
) : ObserveRestaurantFavoriteStatusUseCase {
    override operator fun invoke(restaurant: Restaurant) = MediatorLiveData<Boolean>().apply {
        value = restaurant.isFavorite
        addSource(repository.observeFavoriteStatusBy(restaurant.name)) {
            value = it
            restaurant.isFavorite = it
        }
    }
}
