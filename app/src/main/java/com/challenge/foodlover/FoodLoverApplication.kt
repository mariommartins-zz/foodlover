package com.challenge.foodlover

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level

class FoodLoverApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin()
    }

    private fun startKoin() = org.koin.core.context.startKoin {
        androidLogger(Level.DEBUG)
        androidContext(this@FoodLoverApplication)
//        modules(dataModule + domainModule + featuresModule)
    }
}
