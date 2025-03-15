package com.example.aitutor.screens.splash

import com.example.aitutor.base.reducer.Reducer
import com.example.aitutor.base.reducer.ReducerResult
import com.example.aitutor.base.reducer.UiEffect
import com.example.aitutor.base.reducer.UiEvent
import com.example.aitutor.base.reducer.UiState

data class SplashState(val isLoading: Boolean = true): UiState

sealed interface SplashEffect: UiEffect {
    data class Error(val throwable: Throwable): SplashEffect
    data object NavigateToHome: SplashEffect
}
sealed interface SplashEvent: UiEvent {
    data object InitSuccess: SplashEvent
    data class InitError(val error: Throwable) : SplashEvent
}

class SplashReducer: Reducer<SplashState, SplashEvent, SplashEffect> {
    override fun reduce(
        state: SplashState,
        event: SplashEvent
    ): ReducerResult<SplashState, SplashEffect> {
        return when (event) {
            SplashEvent.InitSuccess -> {
                ReducerResult(
                    state = SplashState(isLoading = false),
                    effect = SplashEffect.NavigateToHome
                )
            }

            is SplashEvent.InitError -> {
                ReducerResult(
                    state = SplashState(isLoading = false),
                    effect = SplashEffect.Error(event.error)
                )
            }
        }
    }
}