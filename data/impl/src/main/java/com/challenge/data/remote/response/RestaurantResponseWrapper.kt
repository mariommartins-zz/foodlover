package com.challenge.data.remote.response

import com.google.gson.annotations.SerializedName

internal data class RestaurantResponseWrapper(
    @SerializedName("restaurants") val restaurants: ArrayList<RestaurantResponse>
)
