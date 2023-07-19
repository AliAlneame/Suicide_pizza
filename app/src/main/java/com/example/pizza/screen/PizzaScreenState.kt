package com.example.pizza.screen

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.pizza.R

data class PizzaScreenState(
    val currentPage: Int = 0,
    val plateImages: List<Int> = listOf(
        R.drawable.bread_1,
        R.drawable.bread_2,
        R.drawable.bread_3,
        R.drawable.bread_4,
        R.drawable.bread_5
    ),
    val toppings: List<Int> = listOf(
        R.drawable.basil_group,
        R.drawable.brocoli_group,
        R.drawable.mushroom_group,
        R.drawable.onion_group,
        R.drawable.susage_group
    ),
    val toppingssingle: List<Int> = listOf(
        R.drawable.basil,
        R.drawable.broccoli,
        R.drawable.mushroom,
        R.drawable.onion,
        R.drawable.sausage
    ),
    val selectedToppings: List<List<Boolean>> = List(5) { List(5) { false } },
    val pizzaSizes: List<Dp> = List(5) { 230.dp }
)