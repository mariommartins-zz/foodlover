package com.challenge.foodlover.feature.restaurantitem

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.ListAdapter
import com.challenge.domain.model.Restaurant
import com.challenge.foodlover.databinding.ItemRestaurantBinding

class RestaurantAdapter(private val lifecycleOwner: LifecycleOwner) :
    ListAdapter<Restaurant, RestaurantItemViewHolder>(RestaurantDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        RestaurantItemViewHolder(
            ItemRestaurantBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            lifecycleOwner
        )

    override fun onBindViewHolder(holder: RestaurantItemViewHolder, position: Int) {
        holder.bind(getItem(position) ?: return)
    }

    override fun onViewRecycled(holder: RestaurantItemViewHolder) = holder.recycle()
}
