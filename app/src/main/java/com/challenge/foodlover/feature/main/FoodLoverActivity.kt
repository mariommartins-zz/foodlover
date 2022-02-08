package com.challenge.foodlover.feature.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.challenge.foodlover.R
import com.challenge.foodlover.databinding.ActivityFoodLoverBinding

class FoodLoverActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil
            .setContentView<ActivityFoodLoverBinding>(this, R.layout.activity_food_lover)
            .apply {
                lifecycleOwner = this@FoodLoverActivity
            }
    }
}