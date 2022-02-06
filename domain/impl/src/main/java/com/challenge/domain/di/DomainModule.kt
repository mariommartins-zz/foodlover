package com.challenge.domain.di

import com.challenge.domain.dispatcher.DispatcherMap
import com.challenge.domain.dispatcher.MainDispatcherMap
import com.challenge.domain.usecase.GetSortedRestaurantList
import com.challenge.domain.usecase.GetSortedRestaurantListUseCase
import org.koin.dsl.module

private val dispatchersModule = module {
    single<DispatcherMap> { MainDispatcherMap() }
}

private val useCaseModule = module {
    factory<GetSortedRestaurantListUseCase> {
        GetSortedRestaurantList(repository = get())
    }
}

val domainModule = dispatchersModule + useCaseModule