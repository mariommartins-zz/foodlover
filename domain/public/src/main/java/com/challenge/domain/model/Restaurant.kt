package com.challenge.domain.model

data class Restaurant(
    val name: String,
    val status: RestaurantOpenStatus,
    val bestMatch: Int,
    val newest: Int,
    val ratingAverage: Double,
    val distance: Int,
    val popularity: Int,
    val averageProductPrice: Int,
    val deliveryCosts: Int,
    val minCost: Int,
    var isFavorite: Boolean = false
)

enum class RestaurantOpenStatus(val priority: Int) {
    OPEN(0),
    ORDER_AHEAD(1),
    CLOSED(2);

    companion object {
        private const val SPACE_CHAR = " "
        private const val UNDERLINE_CHAR = "_"

        fun safeValueOf(value: String): RestaurantOpenStatus? {
            val formattedValue = value
                .replace(SPACE_CHAR, UNDERLINE_CHAR)
                .uppercase()

            return try {
                valueOf(formattedValue)
            } catch (e: IllegalArgumentException) {
                e.printStackTrace()
                null
            }
        }
    }
}