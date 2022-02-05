package com.challenge.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.challenge.data.local.dao.RestaurantDao
import com.challenge.data.local.entity.RestaurantEntity

internal object FoodLoverDatabaseClientBuilder {

    private const val TABLE_NAME = "foodlover_database"

    fun build(context: Context) = Room.databaseBuilder(
        context,
        FoodLoverDatabase::class.java,
        TABLE_NAME
    ).build()
}
