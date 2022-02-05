package com.challenge.data.remote.response

import com.google.gson.annotations.SerializedName

internal data class RestaurantSortingValuesResponse(
    @SerializedName("bestMatch") val bestMatch: Int,
    @SerializedName("newest") val newest: Int,
    @SerializedName("ratingAverage") val ratingAverage: Double,
    @SerializedName("distance") val distance: Int,
    @SerializedName("popularity") val popularity: Int,
    @SerializedName("averageProductPrice") val averageProductPrice: Int,
    @SerializedName("deliveryCosts") val deliveryCosts: Int,
    @SerializedName("minCost") val minCost: Int
)
