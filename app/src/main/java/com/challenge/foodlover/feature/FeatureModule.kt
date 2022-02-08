package com.challenge.foodlover.feature

import com.challenge.foodlover.feature.restaurantdetails.restaurantDetailsModule
import com.challenge.foodlover.feature.restaurantitem.restaurantItemModule
import com.challenge.foodlover.feature.restaurantlist.restaurantListModule
import com.challenge.foodlover.feature.test.testModule

val featureModule = testModule +
        restaurantItemModule +
        restaurantDetailsModule +
        restaurantListModule
