package com.challenge.data.remote.response

import com.google.gson.annotations.SerializedName

internal data class RestaurantResponse(
    @SerializedName("name") val name: String,
    @SerializedName("status") val status: String,
    @SerializedName("sortingValues") val sortingValues: RestaurantSortingValuesResponse
)
