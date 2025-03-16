package com.example.aitutor.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.text.TextStyle

@Immutable
data class AppTypography(
    @Stable
    val bodyLarge: TextStyle,
    @Stable
    val textSplash: TextStyle,
    @Stable
    val button: TextStyle,
    @Stable
    val title: TextStyle,
    @Stable
    val body: TextStyle
)