package com.challenge.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.challenge.data.local.entity.RestaurantEntity

@Dao
internal interface RestaurantDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(restaurant: RestaurantEntity)

    @Query("DELETE FROM restaurant WHERE name = :name")
    suspend fun deleteBy(name: String)

    @Query("SELECT * FROM restaurant WHERE name = :name")
    suspend fun getBy(name: String): RestaurantEntity?

    @Query("SELECT * FROM restaurant WHERE name = :name")
    fun observeBy(name: String): LiveData<RestaurantEntity?>
}
