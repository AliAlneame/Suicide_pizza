package com.example.pizza.screen

import android.util.Log
import androidx.compose.ui.unit.Dp
import com.example.pizza.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PizzaScreenViewModel @Inject constructor():
    BaseViewModel<PizzaScreenState>(PizzaScreenState()) {
    fun toggleTopping(page: Int, topping: Int) {
        Log.d("PizzaScreenViewModel", "toggleTopping called with page = $page and topping = $topping")
        _state.value = _state.value.copy(
            selectedToppings = _state.value.selectedToppings.toMutableList().apply {
                this[page] = this[page].toMutableList().apply {
                    this[topping] = !this[topping]
                }
            }
        )
        Log.d("PizzaScreenViewModel", "selectedToppings after update = ${_state.value.selectedToppings}")
    }


    fun changePizzaSize(page: Int, size: Dp) {
        _state.value = _state.value.copy(
            pizzaSizes = _state.value.pizzaSizes.toMutableList().apply {
                this[page] = size
            }
        )
    }
    fun selectTopping(page: Int, topping: Int, isSelected: Boolean) {
        _state.value = _state.value.copy(
            selectedToppings = _state.value.selectedToppings.toMutableList().apply {
                this[page] = this[page].toMutableList().apply {
                    this[topping] = isSelected
                }
            }
        )
    }
}
