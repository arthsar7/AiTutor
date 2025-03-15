package com.example.aitutor.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalConfiguration

@Immutable
object Theme {

    val typography: AppTypography
        @Composable
        get() = LocalAppTypography.current

    val colors: AppColors
        @Composable
        get() = LocalAppColors.current

    val shapes: AppShapes
        @Composable
        get() = LocalAppShapes.current
}

@Composable
fun AiTutorTheme(
    screenWidth: Float = LocalConfiguration.current.screenWidthDp.toFloat(),
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalAppTypography provides AppThemeImpl.typography,
        LocalAppColors provides AppThemeImpl.colors,
        LocalAppShapes provides AppThemeImpl.shapes,
        content = content
    )
}

val LocalAppTypography = staticCompositionLocalOf<AppTypography> {
    error("No AppTypography provided")
}

val LocalAppColors = staticCompositionLocalOf<AppColors> {
    error("No AppColors provided")
}

val LocalAppShapes = staticCompositionLocalOf<AppShapes> {
    error("No AppShapes provided")
}