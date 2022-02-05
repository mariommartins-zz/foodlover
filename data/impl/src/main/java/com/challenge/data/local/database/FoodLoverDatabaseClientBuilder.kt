package com.challenge.data.local.database

import android.content.Context
import androidx.room.Room

internal object FoodLoverDatabaseClientBuilder {

    private const val TABLE_NAME = "foodlover_database"

    fun build(context: Context) = Room.databaseBuilder(
        context,
        FoodLoverDatabase::class.java,
        TABLE_NAME
    ).build()
}
