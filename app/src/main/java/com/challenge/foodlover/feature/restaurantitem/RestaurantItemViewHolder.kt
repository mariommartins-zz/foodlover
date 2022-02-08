package com.challenge.foodlover.feature.restaurantitem

import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.challenge.common.android.get
import com.challenge.domain.model.Restaurant
import com.challenge.foodlover.databinding.ItemRestaurantBinding
import org.koin.core.parameter.parametersOf

class RestaurantItemViewHolder(
    private val binding: ItemRestaurantBinding,
    lifecycleOwner: LifecycleOwner,
    private val onItemClick: (Restaurant) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    private var viewModel: RestaurantItemViewModel? = null

    init {
        binding.lifecycleOwner = lifecycleOwner
    }

    fun bind(item: Restaurant) {
        viewModel = get<RestaurantItemViewModel> {
            parametersOf(item, onItemClick)
        }.apply { binding.viewModel = this }
    }

    fun recycle() {
        viewModel = null
    }
}
