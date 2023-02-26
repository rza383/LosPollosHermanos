package com.example.lospolloshermanos.food_model

import androidx.annotation.DrawableRes

data class FoodItem (
    @DrawableRes val imageResourceId: Int,
    val name: String,
    val price: Double,
    val description: String
)