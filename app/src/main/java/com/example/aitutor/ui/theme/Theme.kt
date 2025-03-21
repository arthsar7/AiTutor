package com.example.aitutor.ui.theme

import androidx.compose.material3.ProvideTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalConfiguration

@Immutable
object Theme {

    val typography: AppTypography
        @ReadOnlyComposable
        @Composable
        get() = LocalAppTypography.current

    val colors: AppColors
        @ReadOnlyComposable
        @Composable
        get() = LocalAppColors.current

    val shapes: AppShapes
        @ReadOnlyComposable
        @Composable
        get() = LocalAppShapes.current

    val elevations: AppElevations
        @ReadOnlyComposable
        @Composable
        get() = LocalAppElevations.current
}

@Composable
fun Theme(
    screenWidth: Float = LocalConfiguration.current.screenWidthDp.toFloat(),
    appTheme: AppTheme = AppThemeImpl,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalAppTypography provides appTheme.typography,
        LocalAppColors provides appTheme.colors,
        LocalAppShapes provides appTheme.shapes,
        LocalAppElevations provides appTheme.elevations
    ) {
        ProvideTextStyle(value = Theme.typography.body, content = content)
    }
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

val LocalAppElevations = staticCompositionLocalOf<AppElevations> {
    error("No AppElevations provided")
}