package com.challenge.domain.model

enum class RestaurantFilterOption(val value: Int) {
    BEST_MATCH(0),
    NEWEST(1),
    RATING_AVERAGE(2),
    DISTANCE(3),
    POPULARITY(4),
    AVERAGE_PRODUCT_PRICE(5),
    DELIVERY_COSTS(6),
    MIN_COST(7);

    companion object {

        fun getByValue(filterValue: Int) = values().find { it.value == filterValue }
    }
}