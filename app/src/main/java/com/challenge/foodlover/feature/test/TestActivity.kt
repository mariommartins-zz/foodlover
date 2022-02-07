package com.challenge.foodlover.feature.test

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.challenge.domain.model.Restaurant
import com.challenge.domain.model.RestaurantOpenStatus
import com.challenge.foodlover.R
import com.challenge.foodlover.databinding.ActivityTestBinding
import com.challenge.foodlover.feature.main.FoodLoverActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class TestActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTestBinding
    private val viewModel: TestViewModel by viewModel()
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil
            .setContentView<ActivityTestBinding>(this, R.layout.activity_test)
            .apply {
                lifecycleOwner = this@TestActivity
                viewModel = this@TestActivity.viewModel
            }

        setupViews()
        setupObserver()
    }

    private fun setupViews() {
        adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1)
        binding.list.adapter = adapter
        binding.list.setOnItemClickListener { _, _, position, _ ->
            viewModel.toggleFavoriteRestaurant(position)
        }
    }

    private fun setupObserver() {
        viewModel.restaurants.observe(this) { restaurants ->
            adapter.clear()
            adapter.addAll(restaurants.map { testRestaurant(it) })
            adapter.notifyDataSetChanged()
        }

        viewModel.onMainClicked.observe(this) {
            startActivity(Intent(this, FoodLoverActivity::class.java))
        }
    }

    private fun testRestaurant(item: Restaurant): String {
        val stringBuilder = StringBuilder()
        if (item.isFavorite)
            stringBuilder.append("F - ")
        else
            stringBuilder.append("U - ")
        when (item.status) {
            RestaurantOpenStatus.OPEN -> stringBuilder.append("O_ - ")
            RestaurantOpenStatus.ORDER_AHEAD -> stringBuilder.append("OA - ")
            RestaurantOpenStatus.CLOSED -> stringBuilder.append("C_ - ")
        }
        stringBuilder.append(item.name)
        return stringBuilder.toString()
    }
}