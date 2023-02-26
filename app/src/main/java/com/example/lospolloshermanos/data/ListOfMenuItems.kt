package com.example.lospolloshermanos.data

import com.example.lospolloshermanos.R
import com.example.lospolloshermanos.food_model.FoodItem

object ListOfMenuItems {
    val items: List<FoodItem> = listOf(
        FoodItem(
            R.drawable.burrito,
            "Crazy 8's burrito",
            5.99,
            "Crazy fresh burrito Mexicana from the best chef in Tihuana"

        ),
        FoodItem(
            R.drawable.chicken_bowl,
            "Skyler's healthy chicken bowl",
            7.50,
            "Fresh, healthy, homemade, Walter Jr.approved"
        ),
        FoodItem(
            R.drawable.pie,
            "Pinkman Chicken Pie",
            4.00,
        "Jessie's reciped developed over in Alaska"
        ),
        FoodItem(
            R.drawable.chicken_burger,
            "Hank's crispy Chicken Burger",
            7.50,
            "Deluxe burger, best grilled chicken ABQ can offer"
        ),
        FoodItem(
            R.drawable.fried_chicken,
            "Soul Fried Chicken",
            10.00,
            "Good Soul chicken, pun intended"
        ),
        FoodItem(
            R.drawable.wings,
            "Heizenberg's wings",
        9.00,
            "Best recipe in New Mexico, if you know what we mean"
        )
    )
}