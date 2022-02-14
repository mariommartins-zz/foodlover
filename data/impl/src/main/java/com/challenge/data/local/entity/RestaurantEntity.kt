package com.challenge.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "restaurant")
internal data class RestaurantEntity(
    @PrimaryKey @ColumnInfo(name = "name") val name: String
)
