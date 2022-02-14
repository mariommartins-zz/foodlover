package com.challenge.foodlover.feature.restaurantdetails

import com.challenge.domain.model.RestaurantOpenStatus
import com.challenge.foodlover.R
import com.challenge.foodlover.util.robot.clickOnView
import com.challenge.foodlover.util.robot.matchIsChecked
import com.challenge.foodlover.util.robot.matchIsNotChecked
import com.challenge.foodlover.util.robot.matchText
import com.challenge.foodlover.util.robot.matchTextAtIncludedView
import com.challenge.foodlover.util.robot.swipeTo

internal object RestaurantDetailsRobot {
    fun clickToFavoriteRestaurant() {
        clickOnView(viewId = R.id.restaurant_details_favorite_ib)
    }

    fun matchRestaurantStatus(status: RestaurantOpenStatus) {
        val textId = when (status) {
            RestaurantOpenStatus.OPEN -> R.string.restaurant_open_status_bar_text
            RestaurantOpenStatus.ORDER_AHEAD -> R.string.restaurant_order_ahead_status_bar_text
            RestaurantOpenStatus.CLOSED -> R.string.restaurant_closed_status_bar_text
        }
        matchText(
            viewId = R.id.restaurant_details_status_bar_txt,
            stringId = textId
        )
    }

    fun matchRestaurantIsFavorite(favorite: Boolean) {
        if (favorite)
            matchIsChecked(viewId = R.id.restaurant_details_favorite_ib)
        else
            matchIsNotChecked(viewId = R.id.restaurant_details_favorite_ib)
    }

    fun matchRestaurantName(text: String) {
        matchText(viewId = R.id.restaurant_details_name_txt, text = text)
    }

    fun matchRestaurantBestMatchValue(text: String) {
        swipeTo(viewId = R.id.restaurant_details_best_match_container)
        matchTextAtIncludedView(
            containerId = R.id.restaurant_details_best_match_container,
            viewId = R.id.include_restaurant_info_value,
            text = text
        )
    }

    fun matchRestaurantNewestValue(text: String) {
        swipeTo(viewId = R.id.restaurant_details_newest_container)
        matchTextAtIncludedView(
            containerId = R.id.restaurant_details_newest_container,
            viewId = R.id.include_restaurant_info_value,
            text = text
        )
    }

    fun matchRestaurantRatingAverageValue(text: String) {
        swipeTo(viewId = R.id.restaurant_details_rating_average_container)
        matchTextAtIncludedView(
            containerId = R.id.restaurant_details_rating_average_container,
            viewId = R.id.include_restaurant_info_value,
            text = text
        )
    }

    fun matchRestaurantDistanceValue(text: String) {
        swipeTo(viewId = R.id.restaurant_details_distance_container)
        matchTextAtIncludedView(
            containerId = R.id.restaurant_details_distance_container,
            viewId = R.id.include_restaurant_info_value,
            text = text
        )
    }

    fun matchRestaurantPopularityValue(text: String) {
        swipeTo(viewId = R.id.restaurant_details_popularity_container)
        matchTextAtIncludedView(
            containerId = R.id.restaurant_details_popularity_container,
            viewId = R.id.include_restaurant_info_value,
            text = text
        )
    }

    fun matchRestaurantAverageProductPriceValue(text: String) {
        swipeTo(viewId = R.id.restaurant_details_average_product_price_container)
        matchTextAtIncludedView(
            containerId = R.id.restaurant_details_average_product_price_container,
            viewId = R.id.include_restaurant_info_value,
            text = text
        )
    }

    fun matchRestaurantDeliveryCostValue(text: String) {
        swipeTo(viewId = R.id.restaurant_details_delivery_cost_container)
        matchTextAtIncludedView(
            containerId = R.id.restaurant_details_delivery_cost_container,
            viewId = R.id.include_restaurant_info_value,
            text = text
        )
    }

    fun matchRestaurantMinimumCostValue(text: String) {
        swipeTo(viewId = R.id.restaurant_details_minimum_cost_container)
        matchTextAtIncludedView(
            containerId = R.id.restaurant_details_minimum_cost_container,
            viewId = R.id.include_restaurant_info_value,
            text = text
        )
    }
}

internal fun restaurantDetailsState(block: RestaurantDetailsRobot.() -> Unit) =
    RestaurantDetailsRobot.block()
