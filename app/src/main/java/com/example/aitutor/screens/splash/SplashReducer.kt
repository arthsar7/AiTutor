package com.example.aitutor.screens.splash

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.example.aitutor.base.reducer.Reducer
import com.example.aitutor.base.reducer.ReducerResult
import com.example.aitutor.base.reducer.UiEffect
import com.example.aitutor.base.reducer.UiEvent
import com.example.aitutor.base.reducer.UiState

@Immutable
data class SplashState(val isLoading: Boolean = true) : UiState

@Immutable
sealed interface SplashEffect : UiEffect {
    @Stable
    data class Error(val throwable: Throwable) : SplashEffect

    @Stable
    data object NavigateToHome : SplashEffect
}

@Immutable
sealed interface SplashEvent : UiEvent {
    @Stable
    data object InitSuccess : SplashEvent

    @Stable
    data class InitError(val error: Throwable) : SplashEvent
}

class SplashReducer : Reducer<SplashState, SplashEvent, SplashEffect> {
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