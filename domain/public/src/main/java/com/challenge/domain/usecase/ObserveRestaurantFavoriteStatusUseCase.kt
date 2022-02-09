package com.challenge.domain.usecase

import androidx.lifecycle.LiveData
import com.challenge.domain.model.Restaurant

interface ObserveRestaurantFavoriteStatusUseCase {
    operator fun invoke(restaurant: Restaurant): LiveData<Boolean>
}
