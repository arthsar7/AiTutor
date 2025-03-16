package com.example.aitutor.ui.theme

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable

@Immutable
data class AppShapes(
    @Stable
    val small: CornerBasedShape,
    @Stable
    val medium: CornerBasedShape,
    @Stable
    val circle: CornerBasedShape
)
