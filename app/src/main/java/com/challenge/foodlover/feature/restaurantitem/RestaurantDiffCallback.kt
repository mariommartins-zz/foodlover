package com.challenge.foodlover.feature.restaurantitem

import androidx.recyclerview.widget.DiffUtil
import com.challenge.domain.model.Restaurant

object RestaurantDiffCallback : DiffUtil.ItemCallback<Restaurant>() {
    override fun areItemsTheSame(oldItem: Restaurant, newItem: Restaurant) =
        oldItem.name == newItem.name

    override fun areContentsTheSame(oldItem: Restaurant, newItem: Restaurant) = oldItem == newItem
}
