package com.challenge.foodlover.feature.restaurantdetails

import androidx.lifecycle.viewModelScope
import com.challenge.common.android.presentationarch.ViewModel
import com.challenge.domain.model.Restaurant
import com.challenge.domain.usecase.ObserveRestaurantFavoriteStatusUseCase
import com.challenge.domain.usecase.ToggleRestaurantFavoriteStatusUseCase
import com.challenge.kotlin.dispatchers.DispatcherMap
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
    override val action: IRestaurantDetailsViewAction get() = this

    override fun toggleFavoriteStatus() {
        viewModelScope.launch(dispatcherMap.io) { toggleRestaurantFavoriteStatus(restaurant) }
    }
}
