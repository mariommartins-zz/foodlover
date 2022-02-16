package com.challenge.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.challenge.data.local.entity.RestaurantEntity

@Database(entities = [RestaurantEntity::class], version = 1, exportSchema = false)
internal abstract class FoodLoverDatabaseClient : RoomDatabase() {

    abstract fun restaurantDao(): RestaurantDao

    companion object {
        private const val TABLE_NAME = "foodlover_database"

        fun build(context: Context) = Room.databaseBuilder(
            context,
            FoodLoverDatabaseClient::class.java,
            TABLE_NAME
        ).build()
    }
}
