package com.challenge.foodlover.feature.restaurantitem

import androidx.lifecycle.viewModelScope
import com.challenge.common.android.presentationarch.ViewModel
import com.challenge.domain.model.Restaurant
import com.challenge.domain.usecase.ObserveRestaurantFavoriteStatusUseCase
import com.challenge.domain.usecase.ToggleRestaurantFavoriteStatusUseCase
import com.challenge.kotlin.dispatchers.DispatcherMap
import kotlinx.coroutines.launch

class RestaurantItemViewModel(
    private val dispatcherMap: DispatcherMap,
    private val restaurant: Restaurant,
    private val onItemClicked: (Restaurant) -> Unit,
    observeRestaurantFavoriteStatus: ObserveRestaurantFavoriteStatusUseCase,
    private val toggleRestaurantFavoriteStatus: ToggleRestaurantFavoriteStatusUseCase,
    private val mutableState: RestaurantItemViewState =
        RestaurantItemViewState(restaurant, observeRestaurantFavoriteStatus)
) : ViewModel<IRestaurantItemViewState, IRestaurantItemViewAction>(), IRestaurantItemViewAction {

    override val state: IRestaurantItemViewState get() = mutableState
    override val action: IRestaurantItemViewAction get() = this

    override fun toggleFavoriteStatus() {
        viewModelScope.launch(dispatcherMap.io) { toggleRestaurantFavoriteStatus(restaurant) }
    }

    override fun onCardClicked() = onItemClicked(restaurant)
}
