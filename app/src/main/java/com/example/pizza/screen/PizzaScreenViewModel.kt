package com.example.pizza.screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.unit.Dp
import com.example.pizza.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class PizzaScreenViewModel @Inject constructor():
    BaseViewModel<PizzaScreenState>(PizzaScreenState()) {

    private val _pizzaState = mutableStateOf(PizzaScreenState())
    val pizzaState: State<PizzaScreenState> get() = _pizzaState

    init {
        _state.value = PizzaScreenState()
    }

    fun toggleTopping(page: Int, topping: Int) {
        _pizzaState.value = _pizzaState.value.copy(
            selectedToppings = _pizzaState.value.selectedToppings.toMutableList().also {
                it[page] = it[page].toMutableList().also { it[topping] = !it[topping] }
            }
        )
        _state.value = _pizzaState.value
    }

    fun changePizzaSize(page: Int, size: Dp) {
        _pizzaState.value = _pizzaState.value.copy(
            pizzaSizes = _pizzaState.value.pizzaSizes.toMutableList().apply {
                this[page] = size
            }
        )
        _state.value = _pizzaState.value
    }

    fun selectTopping(page: Int, topping: Int, isSelected: Boolean) {
        _pizzaState.value = _pizzaState.value.copy(
            selectedToppings = _pizzaState.value.selectedToppings.toMutableList().apply {
                this[page] = this[page].toMutableList().apply {
                    this[topping] = isSelected
                }
            }
        )
        _state.value = _pizzaState.value
    }
}
