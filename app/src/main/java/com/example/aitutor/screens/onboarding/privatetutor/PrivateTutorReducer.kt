package com.example.aitutor.screens.onboarding.privatetutor

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.example.aitutor.base.reducer.Reducer
import com.example.aitutor.base.reducer.ReducerResult
import com.example.aitutor.base.reducer.UiEffect
import com.example.aitutor.base.reducer.UiEvent
import com.example.aitutor.base.reducer.UiState

@Immutable
data class PrivateTutorState(val isGetStartedClicked: Boolean = false) : UiState

@Immutable
sealed interface PrivateTutorEvent : UiEvent {
    @Stable
    data object GetStartedClicked : PrivateTutorEvent
}

@Immutable
sealed interface PrivateTutorEffect : UiEffect {
    @Stable
    data object NavigateToStart : PrivateTutorEffect
}

class PrivateTutorReducer : Reducer<PrivateTutorState, PrivateTutorEvent, PrivateTutorEffect> {
    override fun reduce(
        state: PrivateTutorState,
        event: PrivateTutorEvent
    ): ReducerResult<PrivateTutorState, PrivateTutorEffect> {
        return when (event) {
            PrivateTutorEvent.GetStartedClicked -> {
                ReducerResult(
                    state = PrivateTutorState(isGetStartedClicked = true),
                    effect = PrivateTutorEffect.NavigateToStart
                )
            }
        }
    }

}