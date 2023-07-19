package com.example.pizza_task.screens.pizza_order_screen.viewmodel

data class PizzaOrderUIState(
    val id: Int = 0,
    val breadList: List<Bread> = emptyList(),
)

data class Bread(
    val id: Int = 0,
    val image: Int = 0,
    val defaultPrice: Int = 0,
    val totalPrice: Int = 0,
    var size: Size = Size.MEDIUM,
    val toppings: List<Topping> = emptyList(),
)

data class Topping(
    val id: Int = 0,
    val name: String = "",
    val mainImage: Int = 0,
    val price: Int = 0,
    val image: Int = 0,
    var isSelected: Boolean = false,
)

enum class Size(val price: Int) {
    SMALL(0),
    MEDIUM(10),
    LARGE(20),
}