package com.example.aitutor.screens

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable

@Immutable
sealed class Screen(val route: String) {
    @Stable
    data object Splash : Screen("splash")

    @Stable
    data object OnboardingPrivateTutor : Screen("onboarding_private_tutor")
}