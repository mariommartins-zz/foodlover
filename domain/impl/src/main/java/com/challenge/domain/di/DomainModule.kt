package com.challenge.domain.di

import com.challenge.domain.dispatcher.DispatcherMap
import com.challenge.domain.dispatcher.MainDispatcherMap
import com.challenge.domain.usecase.*
import com.challenge.domain.usecase.GetSortedRestaurantList
import com.challenge.domain.usecase.ToggleRestaurantFavoriteStatus
import org.koin.dsl.module

private val dispatchersModule = module {
    single<DispatcherMap> { MainDispatcherMap() }
}

private val useCaseModule = module {
    factory<GetSortedRestaurantListUseCase> {
        GetSortedRestaurantList(repository = get())
    }
    factory<ToggleRestaurantFavoriteStatusUseCase> {
        ToggleRestaurantFavoriteStatus(repository = get())
    }
    factory<ObserveRestaurantFavoriteStatusUseCase> {
        ObserveRestaurantFavoriteStatus(repository = get())
    }
}

val domainModule = dispatchersModule + useCaseModule
