package com.challenge.foodlover.binding

import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.challenge.domain.model.RestaurantOpenStatus
import com.challenge.foodlover.R

@BindingAdapter(
    value = ["toggleStatus", "toggledDescription", "untoggledDescription"],
    requireAll = false
)
fun View.setupAsToggleButton(
    toggleStatus: Boolean,
    checkedDescription: String?,
    uncheckedDescription: String?
) {
    contentDescription = if (toggleStatus) checkedDescription else uncheckedDescription
    isActivated = toggleStatus
}

@BindingAdapter("restaurantStatus")
fun TextView.setupAsRestaurantStatusSign(status: RestaurantOpenStatus?) {
    when (status) {
        RestaurantOpenStatus.OPEN -> {
            setText(R.string.restaurant_open_status_bar_text)
            backgroundTintList = ContextCompat.getColorStateList(context, R.color.green)
        }
        RestaurantOpenStatus.ORDER_AHEAD -> {
            setText(R.string.restaurant_order_ahead_status_bar_text)
            backgroundTintList = ContextCompat.getColorStateList(context, R.color.yellow)
        }
        RestaurantOpenStatus.CLOSED -> {
            setText(R.string.restaurant_closed_status_bar_text)
            backgroundTintList = ContextCompat.getColorStateList(context, R.color.red)
        }
        null -> {
            setText(R.string.restaurant_closed_status_bar_text)
            backgroundTintList = ContextCompat.getColorStateList(context, R.color.red)
        }
    }
}

