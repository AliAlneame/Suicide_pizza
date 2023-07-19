package com.example.pizza

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseViewModel<S>(uiState:S): ViewModel() {

    protected val _state: MutableStateFlow<S> by lazy { MutableStateFlow(uiState) }
    val state: StateFlow<S> by lazy { _state.asStateFlow() }

}