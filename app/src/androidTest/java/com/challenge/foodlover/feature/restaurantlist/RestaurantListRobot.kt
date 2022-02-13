package com.challenge.foodlover.feature.restaurantlist

import androidx.annotation.StringRes
import com.challenge.domain.model.Restaurant
import com.challenge.domain.model.RestaurantOpenStatus
import com.challenge.foodlover.R
import com.challenge.foodlover.feature.restaurantitem.RestaurantItemViewHolder
import com.challenge.foodlover.util.robot.clickOnElementOfDisplayedListItem
import com.challenge.foodlover.util.robot.clickOnElementOfSpinner
import com.challenge.foodlover.util.robot.clickOnView
import com.challenge.foodlover.util.robot.matchIsCheckedOnDisplayedListItem
import com.challenge.foodlover.util.robot.matchIsNotCheckedOnDisplayedListItem
import com.challenge.foodlover.util.robot.matchRatingBarValueOnDisplayedListItem
import com.challenge.foodlover.util.robot.matchSpinnerText
import com.challenge.foodlover.util.robot.matchTextOfDisplayedListItem
import com.challenge.foodlover.util.robot.matchWithItemCount
import com.challenge.foodlover.util.robot.swipeItemOn

internal object RestaurantListRobot {
    fun clickOnRestaurantItem(position: Int) {
        clickOnElementOfDisplayedListItem<RestaurantItemViewHolder>(
            listId = R.id.restaurant_list_container_rv,
            position = position,
            viewId = R.id.item_restaurant_card
        )
    }

    fun clickOnFilterOptionBy(position: Int) {
        clickOnView(R.id.restaurant_list_sorting_options_sp)
        clickOnElementOfSpinner(position)
    }

    fun matchFilterOptionSelected(@StringRes textId: Int) =
        matchSpinnerText(R.id.restaurant_list_sorting_options_sp, textId)

    fun matchAmountOfListedItems(amount: Int) =
        matchWithItemCount(R.id.restaurant_list_container_rv, amount)

    fun matchListedRestaurantCardsValuesBy(expected: List<Restaurant>) =
        expected.forEachIndexed(::matchRestaurantCardBy)

    private fun matchRestaurantCardBy(position: Int, expected: Restaurant) {
        swipeItemOn(R.id.restaurant_list_container_rv, position)

        matchRestaurantRatingAt(position, expected.ratingAverage.toFloat())
        matchRestaurantNameAt(position, expected.name)
        matchRestaurantStatusAt(position, expected.status)
        matchRestaurantIsFavoriteAt(position, expected.isFavorite)
    }

    private fun matchRestaurantRatingAt(position: Int, value: Float) {
        matchRatingBarValueOnDisplayedListItem(
            listId = R.id.restaurant_list_container_rv,
            position = position,
            viewId = R.id.item_restaurant_rb,
            value = value
        )
    }

    private fun matchRestaurantNameAt(position: Int, text: String) {
        matchTextOfDisplayedListItem(
            listId = R.id.restaurant_list_container_rv,
            position = position,
            viewId = R.id.item_restaurant_name_txt,
            text = text
        )
    }

    private fun matchRestaurantStatusAt(position: Int, status: RestaurantOpenStatus) {
        val textId = when (status) {
            RestaurantOpenStatus.OPEN -> R.string.restaurant_open_status_bar_text
            RestaurantOpenStatus.ORDER_AHEAD -> R.string.restaurant_order_ahead_status_bar_text
            RestaurantOpenStatus.CLOSED -> R.string.restaurant_closed_status_bar_text
        }
        matchTextOfDisplayedListItem(
            listId = R.id.restaurant_list_container_rv,
            position = position,
            viewId = R.id.item_restaurant_status_bar_txt,
            textId = textId
        )
    }

    private fun matchRestaurantIsFavoriteAt(position: Int, favorite: Boolean) {
        if (favorite)
            matchIsCheckedOnDisplayedListItem(
                listId = R.id.restaurant_list_container_rv,
                position = position,
                viewId = R.id.item_restaurant_name_txt
            )
        else
            matchIsNotCheckedOnDisplayedListItem(
                listId = R.id.restaurant_list_container_rv,
                position = position,
                viewId = R.id.item_restaurant_name_txt
            )
    }
}

internal fun restaurantListState(block: RestaurantListRobot.() -> Unit) =
    RestaurantListRobot.block()
