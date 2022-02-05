package com.challenge.data.di

import com.challenge.data.remote.datasource.IRestaurantRemoteDataSource
import com.challenge.data.remote.datasource.RestaurantRemoteDataSource
import com.challenge.data.remote.mapper.RestaurantResponseToModelMapper
import com.challenge.data.remote.network.FoodLoverClientBuilder
import org.koin.dsl.module

private val networkModule = module {
    factory { FoodLoverClientBuilder.buildApiClient(context = get(), gson = get()) }

    factory { FoodLoverClientBuilder.buildGson() }
}

private val dataSourceModule = module {

    factory<IRestaurantRemoteDataSource> {
        RestaurantRemoteDataSource(
            api = get(),
            mapper = get<RestaurantResponseToModelMapper>()
        )
    }
}

val dataModule = networkModule + dataSourceModule