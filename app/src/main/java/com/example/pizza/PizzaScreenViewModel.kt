package com.example.pizza

import androidx.compose.ui.unit.Dp
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PizzaScreenViewModel @Inject constructor():BaseViewModel<PizzaScreenState>(PizzaScreenState()) {
    fun toggleTopping(page: Int, topping: Int) {
        _state.value = _state.value.copy(
            selectedToppings = _state.value.selectedToppings.toMutableList().apply {
                this[page] = this[page].toMutableList().apply {
                    this[topping] = !this[topping]
                }
            }
        )
    }

    fun changePizzaSize(page: Int, size: Dp) {
        _state.value = _state.value.copy(
            pizzaSizes = _state.value.pizzaSizes.toMutableList().apply {
                this[page] = size
            }
        )
    }
}