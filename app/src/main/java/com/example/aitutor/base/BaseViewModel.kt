package com.example.aitutor.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aitutor.base.reducer.Reducer
import com.example.aitutor.base.reducer.UiEffect
import com.example.aitutor.base.reducer.UiEvent
import com.example.aitutor.base.reducer.UiState
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<S : UiState, E : UiEvent, F : UiEffect>(
    initialState: S,
    private val reducer: Reducer<S, E, F>
) : ViewModel() {

    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<S> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<F>(
        extraBufferCapacity = 64,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val effect: SharedFlow<F> = _effect.asSharedFlow()

    private val events = MutableSharedFlow<E>(
        extraBufferCapacity = 64,
        onBufferOverflow = BufferOverflow.SUSPEND
    )

    init {
        viewModelScope.launch {
            events.collect { event ->
                handleEvent(event)
            }
        }
    }

    fun sendEvent(event: E) {
        viewModelScope.launch {
            events.emit(event)
        }
    }

    private suspend fun handleEvent(event: E) {
        val result = reducer.reduce(_state.value, event)
        _state.value = result.state
        result.effect?.let { effect ->
            _effect.emit(effect)
        }
    }
}