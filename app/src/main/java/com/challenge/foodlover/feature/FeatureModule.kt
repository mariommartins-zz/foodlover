package com.challenge.foodlover.feature

import com.challenge.foodlover.feature.restaurantdetails.restaurantDetailsModule
import com.challenge.foodlover.feature.restaurantitem.restaurantItemModule
import com.challenge.foodlover.feature.test.testModule
import com.challenge.foodlover.mainModule

val featureModule = testModule +
        mainModule +
        restaurantItemModule +
        restaurantDetailsModule
