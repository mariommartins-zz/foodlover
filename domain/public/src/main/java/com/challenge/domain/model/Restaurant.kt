package com.challenge.domain.model


data class Restaurant(
    val name: String,
    val status: String,
    val bestMatch: Int,
    val newest: Int,
    val ratingAverage: Double,
    val distance: Int,
    val popularity: Int,
    val averageProductPrice: Int,
    val deliveryCosts: Int,
    val minCost: Int
)