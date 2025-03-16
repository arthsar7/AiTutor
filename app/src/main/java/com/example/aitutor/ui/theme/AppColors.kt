package com.example.aitutor.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Immutable
data class AppColors(
    @Stable
    val primary: Color,
    @Stable
    val secondary: Color,
    @Stable
    val secondaryLight: Color,
    @Stable
    val background: Color,
    @Stable
    val buttonGradientBrush: Brush,
    @Stable
    val splashGradientBrush: Brush,
    @Stable
    val lightBlueGradientBrush: Brush
)