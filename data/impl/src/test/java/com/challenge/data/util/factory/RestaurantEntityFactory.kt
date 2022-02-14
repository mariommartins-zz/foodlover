package com.challenge.data.util.factory

import com.challenge.data.local.entity.RestaurantEntity
import com.challenge.testcore.util.RandomGenerator

internal object RestaurantEntityFactory {

    fun make(name: String = RandomGenerator.string()) = RestaurantEntity(name = name)
}