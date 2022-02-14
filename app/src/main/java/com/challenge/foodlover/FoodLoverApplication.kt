package com.challenge.foodlover

import android.app.Application
import com.challenge.data.di.dataModule
import com.challenge.domain.di.domainModule
import com.challenge.foodlover.feature.featureModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class FoodLoverApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin()
    }

    private fun startKoin() = startKoin {
        androidLogger(Level.DEBUG)
        androidContext(this@FoodLoverApplication)
        modules(dataModule + domainModule + featureModule)
    }
}
