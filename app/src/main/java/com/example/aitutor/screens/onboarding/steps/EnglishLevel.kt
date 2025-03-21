package com.example.aitutor.screens.onboarding.steps

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable

@Immutable
enum class EnglishLevel(
    val title: String,
    val description: String
) {
    @Stable
    A1(
        title = "A1 Beginner",
        description = "I know basic words and simple phrases"
    ),

    @Stable
    A2(
        title = "A2 Elementary",
        description = "I can order food and talk about the weather"
    ),

    @Stable
    B1(
        title = "B1 Intermediate",
        description = "I can talk about daily life and share  thoughts"
    ),

    @Stable
    B2(
        title = "B2 Upper-Intermediate",
        description = "I can discuss many topics and debate easily"
    ),

    @Stable
    C1(
        title = "C1 Advanced",
        description = "I speak fluently and understand humor"
    )
}