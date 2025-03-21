package com.example.aitutor.screens.onboarding.steps

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable

@Immutable
enum class NativeLanguage(
    val title: String,
    val nativeTitle: String
) {
    @Stable
    Arabic("Arabic", "عربي"),
    @Stable
    Chinese("Chinese (Simplified)", "中国人"),
    @Stable
    English("English", ""),
    @Stable
    Japanese("Japanese", "日本語"),
    @Stable
    Portuguese("Portuguese", "Português"),
    @Stable
    Spanish("Spanish", "Española"),
    @Stable
    Hindi("Hindi", "हिंदी"),
}