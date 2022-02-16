package com.challenge.data.di

import com.challenge.data.local.database.FoodLoverDatabaseClient
import com.challenge.data.local.datasource.IRestaurantLocalDataSource
import com.challenge.data.local.datasource.RestaurantLocalDataSource
import com.challenge.data.local.mapper.MapRestaurantFromModelToEntity
import com.challenge.data.remote.datasource.IRestaurantRemoteDataSource
import com.challenge.data.remote.datasource.RestaurantRemoteDataSource
import com.challenge.data.remote.mapper.MapRestaurantFromResponseToModel
import com.challenge.data.remote.network.FoodLoverApiClientBuilder
import com.challenge.data.repository.RestaurantRepository
import com.challenge.domain.repository.IRestaurantRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

private val networkModule = module {
    factory { FoodLoverApiClientBuilder.buildGson() }
    single { FoodLoverApiClientBuilder.build(context = androidContext(), gson = get()) }
}

private val databaseModule = module {
    single { FoodLoverDatabaseClient.build(context = androidContext()) }
    single { get<FoodLoverDatabaseClient>().restaurantDao() }
}

private val mapperModule = module {
    factory { MapRestaurantFromModelToEntity() }
    factory { MapRestaurantFromResponseToModel() }
}

private val dataSourceModule = module {
    factory<IRestaurantRemoteDataSource> {
        RestaurantRemoteDataSource(
            api = get(),
            mapFromResponseToModel = get<MapRestaurantFromResponseToModel>()
        )
    }
    factory<IRestaurantLocalDataSource> {
        RestaurantLocalDataSource(
            dao = get(),
            mapFromModelToEntity = get<MapRestaurantFromModelToEntity>()
        )
    }
}

private val repositoryModule = module {
    single<IRestaurantRepository> {
        RestaurantRepository(
            localDataSource = get(),
            remoteDataSource = get()
        )
    }
}

val dataModule = networkModule + databaseModule + mapperModule + dataSourceModule + repositoryModule