package com.challenge.foodlover.feature

import com.challenge.foodlover.feature.restaurantdetails.restaurantDetailsModule
import com.challenge.foodlover.feature.restaurantitem.restaurantItemModule
import com.challenge.foodlover.feature.restaurantlist.restaurantListModule

val featureModule = restaurantItemModule + restaurantDetailsModule + restaurantListModule
