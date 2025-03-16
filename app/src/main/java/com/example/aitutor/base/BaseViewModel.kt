package com.example.aitutor.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aitutor.base.reducer.Reducer
import com.example.aitutor.base.reducer.UiEffect
import com.example.aitutor.base.reducer.UiEvent
import com.example.aitutor.base.reducer.UiState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel<S : UiState, E : UiEvent, F : UiEffect>(
    initialState: S,
    private val reducer: Reducer<S, E, F>
) : ViewModel() {

    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<S> = _state.asStateFlow()

    private val _effect = Channel<F>()
    val effect: Flow<F> = _effect.receiveAsFlow()

    private val events = MutableSharedFlow<E>(extraBufferCapacity = 64, replay = 3)

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
        _state.update {prevState ->
            val (state, effect) = reducer.reduce(prevState, event)
            effect?.let { _effect.send(it) }
            state
        }
    }
}