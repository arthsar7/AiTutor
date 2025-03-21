package com.example.aitutor.screens.onboarding.steps

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.Stable
import androidx.compose.ui.res.stringResource
import com.example.aitutor.R

@Immutable
enum class OnboardingStep(@StringRes private val titleRes: Int) {
    @Stable
    NativeLanguage(R.string.native_language_title),
    @Stable
    EnglishLanguage(R.string.english_language_title),
    @Stable
    StudyReasons(R.string.study_reasons_title),
    @Stable
    YourName(R.string.your_name_title),
    @Stable
    InterestingTopics(R.string.interesting_topics_title),
    @Stable
    PracticeTime(R.string.practice_time_title);

    val title: String
        @Composable
        @ReadOnlyComposable
        get() = stringResource(titleRes)
}