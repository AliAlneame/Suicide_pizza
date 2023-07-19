package com.example.pizza_task.screens.pizza_order_screen.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import com.example.pizza_task.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class PizzaOrderViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(PizzaOrderUIState())
    val state = _state.asStateFlow()

    init {
        _state.update {
            it.copy(
                breadList = getBread(),
            )
        }
    }

    fun onSizeClick(size: Size, breadIndex: Int) {
        _state.update {
            val breadList = it.breadList.toMutableList()
            val bread = breadList[breadIndex]

            breadList[breadIndex] = bread.copy(
                size = size,
                totalPrice = bread.defaultPrice + size.price +
                        bread.toppings.filter { topping -> topping.isSelected }
                            .sumOf { topping -> topping.price },
            )

            it.copy(
                breadList = breadList,
            )
        }
    }

    fun onToppingClick(toppingIndex: Int, breadIndex: Int) {
        _state.update {
            val breadList = it.breadList.toMutableList()
            val bread = breadList[breadIndex]
            val toppings = bread.toppings.toMutableList()
            val topping = toppings[toppingIndex]

            toppings[toppingIndex] = topping.copy(
                isSelected = !topping.isSelected,
            )

            breadList[breadIndex] = bread.copy(
                toppings = toppings,
                totalPrice = bread.totalPrice +
                        if (toppings[toppingIndex].isSelected) topping.price else -topping.price,
            )

            it.copy(
                breadList = breadList,
            )
        }
    }


    private fun getBread(): List<Bread> {
        return listOf(
            Bread(1, R.drawable.bread_1, 30, 40, Size.MEDIUM, getToppings()),
            Bread(2, R.drawable.bread_2, 47, 57, Size.MEDIUM, getToppings()),
            Bread(3, R.drawable.bread_3, 56, 66, Size.MEDIUM, getToppings()),
            Bread(4, R.drawable.bread_4, 68, 78, Size.MEDIUM, getToppings()),
            Bread(5, R.drawable.bread_5, 79, 89, Size.MEDIUM, getToppings()),
        )
    }

    private fun getToppings(): List<Topping> {
        return listOf(
            Topping(1, "Basil", R.drawable.basil, 1, R.drawable.basil_group),
            Topping(2, "Onion", R.drawable.onion, 2, R.drawable.onion_group),
            Topping(3, "Broccoli", R.drawable.broccoli, 5, R.drawable.brocoli_group),
            Topping(4, "Mushroom", R.drawable.mushroom, 8, R.drawable.mushroom_group),
            Topping(5, "Sausage", R.drawable.sausage, 10, R.drawable.susage_group),
        )
    }
}

val toppingslazy = remember {
    mutableStateListOf(
        R.drawable.basil,
        R.drawable.broccoli,
        R.drawable.mushroom,
        R.drawable.onion,
        R.drawable.sausage
    )
}