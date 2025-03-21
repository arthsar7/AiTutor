package com.example.aitutor.ui.theme

import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable

@Immutable
data class AppElevations(
    val medium: Float
) {
    val card: CardElevation
        @Composable
        get() = CardDefaults.elevatedCardElevation(medium.dep)
}
