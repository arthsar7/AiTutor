package com.example.aitutor.screens.onboarding.steps

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.example.aitutor.base.reducer.Reducer
import com.example.aitutor.base.reducer.ReducerResult
import com.example.aitutor.base.reducer.UiEffect
import com.example.aitutor.base.reducer.UiEvent
import com.example.aitutor.base.reducer.UiState
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList


@Immutable
data class StepsState(
    val currentStepIndex: Int = 0,
    val onboardingSteps: ImmutableList<OnboardingStep> = OnboardingStep.entries.toPersistentList(),
    val isContinueAvailable: Boolean = true,
    val nativeLanguages: ImmutableList<NativeLanguage> = NativeLanguage.entries.toPersistentList(),
    val selectedNativeLanguage: NativeLanguage = NativeLanguage.English,
    val englishLevels: ImmutableList<EnglishLevel> = EnglishLevel.entries.toPersistentList(),
    val selectedEnglishLevel: EnglishLevel = EnglishLevel.A1,
    val studyReasons: ImmutableList<StudyReason> = StudyReason.entries.toPersistentList(),
    val selectedStudyReasons: ImmutableList<StudyReason> = persistentListOf(),
    val yourName: String = "",
) : UiState {
    @Stable
    val currentOnboardingStep: OnboardingStep get() = onboardingSteps[currentStepIndex]

    @Stable
    val isLastStep: Boolean get() = currentStepIndex == onboardingSteps.lastIndex

    @Stable
    val isFirstStep: Boolean get() = currentStepIndex == 0
}


@Immutable
sealed interface StepsEvent : UiEvent {
    @Stable
    data object ContinueClicked : StepsEvent

    @Stable
    data class StepChanged(val step: Int) : StepsEvent

    @Stable
    data object BackClicked : StepsEvent

    @Stable
    data class SelectNativeLanguage(val nativeLanguage: NativeLanguage) : StepsEvent

    @Stable
    data class SelectEnglishLevel(val englishLevel: EnglishLevel) : StepsEvent

    @Stable
    data class SelectStudyReason(val studyReason: StudyReason) : StepsEvent

    @Stable
    data class SetYourName(val yourName: String) : StepsEvent
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
                        state = state.copy(
                            currentStepIndex = state.currentStepIndex - 1,
                            isContinueAvailable = validate(state, state.currentStepIndex - 1)
                        ),
                        effect = StepsEffect.NavigateToStep(state.currentStepIndex - 1)
                    )
                }
            }

            StepsEvent.ContinueClicked -> {
                ReducerResult(
                    state = state.copy(
                        currentStepIndex = state.currentStepIndex + 1,
                        isContinueAvailable = validate(state, state.currentStepIndex + 1)
                    ),
                    effect = StepsEffect.NavigateToStep(state.currentStepIndex + 1),
                )
            }

            is StepsEvent.SelectNativeLanguage -> {
                ReducerResult(
                    state = state.copy(selectedNativeLanguage = event.nativeLanguage),
                )
            }

            is StepsEvent.SelectEnglishLevel -> {
                ReducerResult(
                    state = state.copy(selectedEnglishLevel = event.englishLevel),
                )
            }

            is StepsEvent.SelectStudyReason -> {
                val isStudyReasonSelected = state.selectedStudyReasons.contains(event.studyReason)
                val selectedStudyReasons = if (isStudyReasonSelected) {
                    state.selectedStudyReasons - event.studyReason
                } else {
                    state.selectedStudyReasons + event.studyReason
                }
                ReducerResult(
                    state = state.copy(
                        selectedStudyReasons = selectedStudyReasons.toPersistentList(),
                        isContinueAvailable = selectedStudyReasons.isNotEmpty()
                    )
                )
            }

            is StepsEvent.SetYourName -> {
                ReducerResult(
                    state = state.copy(
                        yourName = event.yourName,
                        isContinueAvailable = event.yourName.isNotBlank()
                    ),
                )
            }

            is StepsEvent.StepChanged -> {
                ReducerResult(
                    state = state.copy(
                        currentStepIndex = event.step,
                        isContinueAvailable = validate(state, event.step)
                    ),
                    effect = StepsEffect.NavigateToStep(event.step)
                )
            }
        }
    }

    private fun validate(state: StepsState, currentStepIndex: Int = state.currentStepIndex): Boolean {
        return when (state.onboardingSteps[currentStepIndex]) {
            OnboardingStep.YourName -> state.yourName.isNotBlank()
            OnboardingStep.StudyReasons -> state.selectedStudyReasons.isNotEmpty()
            else -> true
        }
    }

}