package com.challenge.foodlover.feature.restaurantdetails

import androidx.lifecycle.viewModelScope
import com.challenge.domain.dispatcher.DispatcherMap
import com.challenge.domain.model.Restaurant
import com.challenge.domain.usecase.ObserveRestaurantFavoriteStatusUseCase
import com.challenge.domain.usecase.ToggleRestaurantFavoriteStatusUseCase
import com.challenge.foodlover.util.presentationarch.ViewModel
import kotlinx.coroutines.launch

class RestaurantDetailsViewModel(
    private val dispatcherMap: DispatcherMap,
    private val restaurant: Restaurant,
    observeRestaurantFavoriteStatus: ObserveRestaurantFavoriteStatusUseCase,
    private val toggleRestaurantFavoriteStatus: ToggleRestaurantFavoriteStatusUseCase,
    private val mutableState: RestaurantDetailsViewState =
        RestaurantDetailsViewState(restaurant, observeRestaurantFavoriteStatus)
) : ViewModel<IRestaurantDetailsViewState, IRestaurantDetailsViewAction>(),
    IRestaurantDetailsViewAction {

    override val state: IRestaurantDetailsViewState get() = mutableState

    override fun toggleFavoriteStatus() {
        viewModelScope.launch(dispatcherMap.io) { toggleRestaurantFavoriteStatus(restaurant) }
    }
}
