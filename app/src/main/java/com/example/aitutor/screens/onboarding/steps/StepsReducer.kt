package com.example.aitutor.screens.onboarding.steps

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.example.aitutor.base.reducer.Reducer
import com.example.aitutor.base.reducer.ReducerResult
import com.example.aitutor.base.reducer.UiEffect
import com.example.aitutor.base.reducer.UiEvent
import com.example.aitutor.base.reducer.UiState
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toPersistentList

@Immutable
data class StepsState(
    val currentStepIndex: Int = 0,
    val steps: ImmutableList<Step> = Step.entries.toPersistentList(),
) : UiState {
    @Stable
    val currentStep: Step get() = steps[currentStepIndex]

    @Stable
    val isLastStep: Boolean get() = currentStepIndex == steps.lastIndex

    @Stable
    val isFirstStep: Boolean get() = currentStepIndex == 0
}


@Immutable
sealed interface StepsEvent : UiEvent {
    @Stable
    data object ContinueClicked : StepsEvent

    @Stable
    data object BackClicked : StepsEvent
}

@Immutable
sealed interface StepsEffect : UiEffect {
    @Stable
    data class NavigateToStep(val step: Int) : StepsEffect

    @Stable
    data object Back : StepsEffect
}

class StepsReducer : Reducer<StepsState, StepsEvent, StepsEffect> {
    override fun reduce(
        state: StepsState,
        event: StepsEvent
    ): ReducerResult<StepsState, StepsEffect> {
        return when (event) {
            StepsEvent.BackClicked -> {
                if (state.currentStepIndex == 0) {
                    ReducerResult(
                        state = StepsState(),
                        effect = StepsEffect.Back
                    )
                } else {
                    ReducerResult(
                        state = StepsState(currentStepIndex = state.currentStepIndex - 1),
                        effect = StepsEffect.NavigateToStep(state.currentStepIndex - 1)
                    )
                }
            }

            StepsEvent.ContinueClicked -> {
                ReducerResult(
                    state = StepsState(currentStepIndex = state.currentStepIndex + 1),
                    effect = StepsEffect.NavigateToStep(state.currentStepIndex + 1)
                )
            }
        }
    }

}