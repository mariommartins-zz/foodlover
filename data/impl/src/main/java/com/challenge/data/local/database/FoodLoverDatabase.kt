package com.challenge.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.challenge.data.local.dao.RestaurantDao
import com.challenge.data.local.entity.RestaurantEntity

@Database(entities = [RestaurantEntity::class], version = 1, exportSchema = false)
internal abstract class FoodLoverDatabase  : RoomDatabase() {

    abstract fun restaurantDao(): RestaurantDao
}
