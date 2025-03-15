package com.example.aitutor.base.reducer

interface UiState
interface UiEvent
interface UiEffect

interface Reducer<S : UiState, E : UiEvent, F : UiEffect> {
    fun reduce(state: S, event: E): ReducerResult<S, F>
}
